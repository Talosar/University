package server;

import java.io.*;

import java.util.StringTokenizer;

public class ChatDB {
    private String path = ".\\chatDB\\chatdb.cdb";
    private File f;
    
    public ChatDB(){
        f = new File(path);
        if(!f.exists()){
            try {
                System.out.println(f.getAbsolutePath());
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("In ChatDB Constructor catch...");
            }
        }
        
    }
    public boolean setDBPath(String s){
        f = new File(s);
        if(f.exists() && f.isFile()){
            return true;  
        }else {
            return false;
        }
    }
    
    public File creatDB() throws IOException{
        if(f!=null){
                if (f.createNewFile()) { return f; }
                else { return null; }
        }
        return null;
    }
    
    
    public boolean searchClient(String login, String pass){
        if (f!=null){
            String separator = ";;";
            String line = null;
            BufferedReader br = null;
            try{
                try {
                br = new BufferedReader(new FileReader(f));
                while( (line = br.readLine()) != null) {
                    StringTokenizer token = new StringTokenizer(line, separator);
                    if(token.nextToken().equals(login)){
                        if(token.nextToken().equals(pass)){ return true; }
                    }
                }
                }finally {   br.close(); }
            }catch (Exception e) { return false; }
        }
        return false;
    }//searchClient
    
    public void addClient(String login, String pass){
        if(!searchClient(login, pass)){
            try{
                RandomAccessFile out = new RandomAccessFile(f, "rw");
                try {
                    out.seek(f.length());
                    out.write((login+";;"+pass+"\n").getBytes());
                }finally{   out.close();}
            }catch (IOException e) {
            }
        }
    }//addClient
}
