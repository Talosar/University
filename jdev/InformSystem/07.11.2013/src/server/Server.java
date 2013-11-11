package server;
import java.util.*;
import java.io.*;
import java.net.*;

public class Server implements Runnable { 
    private static final int port = 6565;
    private static final String CRLF = "\r\n";
    private int id = 0; //id, назначаемое подключению. ++ для каждого нового клиента
    private Hashtable idcon = new Hashtable();  //хранение подключений
    
    @Override
    public void run() {
        try{
            ServerSocket acceptSocket = new ServerSocket(port);
            System.out.println("Server listening on port "+port);
            while(true){
                Socket s = acceptSocket.accept();
                addConnection(s);
            }
        }catch(IOException e){
            System.out.println("accept loop Exeption: "+e);
        }
    }
    
    public static void main(String args[]){
        new Thread(new Server()).start();
        try{
            Thread.currentThread().join();
        }catch(InterruptedException ie){}
    }
    
    synchronized void addConnection(Socket s){
        ClientConnection con = new ClientConnection(this, s, id);
        id++;
    }
    
    synchronized void set(String the_id, ClientConnection con){
        idcon.remove(the_id);
        //передача подключаемому клиенту списка подключенных клиентов
        Enumeration e = idcon.keys();
        while(e.hasMoreElements()){
            String id = (String)e.nextElement();
            ClientConnection other = (ClientConnection)idcon.get(id);
            con.write("add "+other +CRLF);
        }
        idcon.put(the_id, con);
        //оповещение всех клиентов о подключении нового клиента
        broadcast(the_id, "add "+con);
    }
    
    //отправка сообщения msg конкретному клиенту dest
    synchronized void sendTo(String dest, String msg){
        ClientConnection con = (ClientConnection)idcon.get(dest);
        if(con!=null){
            con.write(msg+CRLF);
        }
    }
    
    //отправка сообщения msg всем клиентам
    synchronized void broadcast(String exclude, String msg){
        Enumeration e = idcon.keys();
        while(e.hasMoreElements()){
            String id = (String)e.nextElement();
            if(!exclude.equals(id)){
                ClientConnection con = (ClientConnection)idcon.get(id);
                con.write(msg+CRLF);   
            }
            
        }
    }
    
    //отправка всем клиентам сообщения о том, что клиент c.getId() отключен от сервера
    synchronized void kill(ClientConnection c){
        if(idcon.remove(c.getId()) == c){
            broadcast(c.getId(), "delete "+c.getId());
        }
    }
}
