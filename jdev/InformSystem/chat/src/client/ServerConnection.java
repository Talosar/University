package client;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JFrame;

public class ServerConnection implements Runnable {
    private static final int port = 6565;
    private static final String CRLF = "\r\n";
    private final String BROADCASTID = "-6";
    private BufferedReader in;
    private PrintWriter out;
    private String id;  //ID, под которым подключение известно на сервере. Назначается сервером.
    private Thread t;
    
    private JFrame mainFrm;
    
    public ServerConnection (String site, JFrame temp) throws IOException{
        Socket server = new Socket(site, port);
        mainFrm = temp;
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
    
    public void login(String login, String pass){ 
        out.println("logon "+login + " " + pass); 
    }
    
    public void delete(){
        out.println("delete");    
    }
    
    public void registration(String login, String pass){
        out.println("reg " + login + " " + pass);
    }
    
    public void send(String s, String destid){
        out.println("to " + destid + " " + s);
    }
    
    public void commonChat(String msg){
        send("commonchat "+ id + " " + msg, BROADCASTID);
    }
    
    public void privateChat(String msg, String destid){
        send("privatechat "+ id + " " + msg, destid);
    }
    
    public void start(){
        t = new Thread(this);
        t.start();
    }
    //
    
    @Override
    public void run() {
        String s;
        StringTokenizer st;
        while((s = readline()) != null){
            System.out.println("In while");
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
                System.out.println("In ADD");
                //tf.addElement(name, id);
                //добавление в список
                }
                break;
            case DELETE:
                //tf.removeElement(id);
                //добавить удаление из списка
                break;
            case PRIVATECHAT: {
                
                }
                break;
            case COMMONCHAT: {
                String id = st.nextToken();
                String msg = st.nextToken(CRLF);
                //tf.append(id + ": "+ msg);
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
