package server;

import java.io.*;

import java.util.StringTokenizer;

public class ChatDB {
    private File f;
    
    
    public boolean setDBPath(String s){
        f = new File(s);
        if(f.exists() && f.isFile()){
            return true;  
        }else {
            return false;
        }
    }
    
    public File creatDB(){
        if(f!=null){
            try {
                if (f.createNewFile()) { return f; }
                else { return null; }
            } catch (IOException e) {
                return null;
            }
        } else { return null; }
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
                        else { return false; }
                    }
                }
                }finally {   br.close(); }
            }catch (Exception e) { return false; }
        }
        return false;
    }//searchClient
    
    public boolean addClient(String login, String pass){
        if(!searchClient(login, pass)){
            try{
                RandomAccessFile out = new RandomAccessFile(f, "rw");
                try {
                    out.seek(f.length());
                    out.write((login+";;"+pass+"\n").getBytes());
                    return true;
                }finally{   out.close();}
            }catch (IOException e) {
                return false;
            }
        }
        return false;
    }
}
