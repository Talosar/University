package server;

import java.net.*;
import java.io.*;
import java.util.*;

class ClientConnection implements Runnable {
    private Socket sock;
    private BufferedReader in;
    private OutputStream out;
    private String host;
    private String name;
    private String id;
    private Server server;
    private final String BROADCASTID = "-6";
    private static final String CRLF = "\r\n";
    
    public ClientConnection(Server srv, Socket s, int i){
        try{
            server = srv;
            sock = s;
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = s.getOutputStream();
            host = s.getInetAddress().getHostName();
            id = ""+i;
            write("id "+ id + CRLF);
            new Thread(this).start();
        }catch (IOException e){
            System.out.println("failed ClientConnection" + e);
        }
    }
    
    public String toString(){
        return id + " " + host + " " + name;
    }
    
    public void write(String s){
        byte buf[];
        buf = s.getBytes();
        try{
            out.write(buf, 0, buf.length);
        } catch (IOException e) {
            close();
        }
    }
    private String readline(){
        try{
            return in.readLine();
        } catch (IOException e){
            return null;
        }
    }
    public String getId(){  return id;  }
    public String getHost(){    return host;    }
    
    public void close(){
        server.kill(this);
        try{
            sock.close();
        } catch (IOException e){}
    }
    
    @Override
    public void run() {
        String s;
        StringTokenizer st;
        while((s = readline())!=null){
            st = new StringTokenizer(s);
            String keyword = st.nextToken();
            switch (lookup(keyword)){
            default:
                System.out.println("bogus keyword: " + keyword + "\r");
                break;
            case NAME:
                name = st.nextToken()+
                       (st.hasMoreElements() ? " "+st.nextToken(CRLF) : "");
                System.out.println("["+ new Date() + "]" + this + "\r");
                server.set(id, this);
                break;
            case DELETE:
                close();
                break;
            case TO:
                String dest = st.nextToken();
                String msg = st.nextToken(CRLF);
                if(dest.equals(BROADCASTID)){
                    server.broadcast(id, msg);
                } else {
                    server.sendTo(dest, msg);
                }
                break;
            }
        }
        close();
    }
    
    /************************************/
    /************* Keywords *************/
    /************************************/
    private static final int NAME = 1;
    private static final int DELETE = 2;
    private static final int TO = 3;
    
    static private Hashtable keys = new Hashtable();
    static private String keystring[] = {
        "", "name", "delete", "to"
    };
    static {
        for (int i=0; i<keystring.length; i++){
            keys.put(keystring[i], new Integer(i));
        }
    }
    
    private int lookup (String s){
        Integer i = (Integer) keys.get(s);
        return i == null ? -1 : i.intValue();
    }
}
