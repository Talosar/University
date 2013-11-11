package client;

import java.io.*;
import java.net.*;
import java.util.*;

class ServerConnection implements Runnable {
    private static final int port = 6565;
    private static final String CRLF = "\r\n";
    private final String BROADCASTID = "-6";
    private BufferedReader in;
    private PrintWriter out;
    private String id;  //ID, под которым подключение известно на сервере. Назначается сервером.
    private Thread t;
    
    private TestFrame tf;
    
    public ServerConnection (String site) throws IOException{
        Socket server = new Socket(site, port);   
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
    }
    
    public String readline(){
        try{
            return in.readLine();
        } catch (IOException e) {
            return null;    
        }
    }
    
    void setName(String s){ 
        out.println("name "+s); 
    }
    
    void delete(){
        out.println("delete");    
    }
    
    void send(String s, String destid){
        out.println("to " + destid + " " + s);
    }
    
    void commonChat(String msg){
        send("commonchat "+ id + " " + msg, BROADCASTID);
    }
    
    void privateChat(String msg, String destid){
        send("privatechat "+ id + " " + msg, destid);
    }
    
    void start(){
        t = new Thread(this);
        t.start();
    }
    //
    
    @Override
    public void run() {
        String s;
        StringTokenizer st;
        while((s = readline()) != null){
            st = new StringTokenizer(s);
            String keyword = st.nextToken();
            switch (lookup(keyword)){
            default:
                System.out.println("bogus keyword: " + keyword + "\r");
                break;
            case ID:
                id = st.nextToken();
                break;
            case ADD:{
                String id = st.nextToken();
                String hostname = st.nextToken();
                String name = st.nextToken(CRLF);
                //добавление в список
                }
                break;
            case DELETE:
                //добавить удаление из списка
                break;
            case PRIVATECHAT: {
                
                }
                break;
            case COMMONCHAT: {
                
                }
                break;
            }
        }
    }//run()
    
    /************************************/
    /************* Keywords *************/
    /************************************/
    private static final int ID = 1;
    private static final int ADD = 2;
    private static final int DELETE = 3;
    private static final int PRIVATECHAT = 4;
    private static final int COMMONCHAT = 5;
    private static Hashtable keys = new Hashtable();
    private static String keystring[] = {
        "", "id", "add", "delete", "privatechat",
        "commonchat"
    };
    static {
        for (int i = 0; i<keystring.length; i++){
            keys.put(keystring[i], new Integer(i));
        }
    }
    private int lookup(String s){
        Integer i = (Integer) keys.get(s);
        return i == null ? -1 : i.intValue();
    }
}
