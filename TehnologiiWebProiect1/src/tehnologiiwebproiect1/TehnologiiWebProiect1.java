/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tehnologiiwebproiect1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.nio;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Boogie
 */

public class TehnologiiWebProiect1 {

    /**
     * @param pageURL
     * @param args the command line arguments
     * @return 
     * @throws java.net.MalformedURLException
     */
    
    public static String getTextFile(String pageURL) throws
            MalformedURLException,IOException{
    
        StringBuilder sb = new StringBuilder();
        URL url = new URL(pageURL);
        String protocol = url.getProtocol();
        if(!protocol.equals("http"))
            throw new IllegalArgumentException("Protocol" + protocol);
        String host = url.getHost();
        int port = url.getPort();
        if(port == -1)
            port = url.getDefaultPort();
        String path = url.getPath();
        
        Socket socket = new Socket(host,port);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        
        
     //   String request = "GET " + path + " HTTP/1.1\r\n"+
               //            "Host: "+ host +"\r\n";
       out.print(("GET /")+ path  + " HTTP/1.1\r\n" +
            "Host: " + host + "\r\n" +
            "Connection: close\r\n" +
            "\r\n"
            );
     //   out.print(request);
        out.flush();
        
        String line = null;
        while((line=in.readLine())!=null){
            sb.append(line);
            sb.append("\n");
        }
        socket.close();
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       String pageUrl="http://inf.ucv.ro/~mihaiug/teaching.html";
       String pageContent = null;
        
      try{
       pageContent = getTextFile(pageUrl);
       System.out.println(pageContent);
       }catch (MalformedURLException e){
           System.err.println("Adresa invalida " + e.getMessage());
       }catch (IOException e){
           System.err.println("Eroare la downloadarea paginii: "+e.getMessage());
      }
       
    }
    
}
