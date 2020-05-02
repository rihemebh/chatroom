import java.io.*;
import java.net.*;
import java.util.*;

public class Server extends Thread{
    private static  ArrayList<clientHandler> clients = new ArrayList<clientHandler>();
    private final ServerSocket s;
    public Server(ServerSocket s){
        this.s=s;
    }

    public static ArrayList<clientHandler> getClients(){
        return clients;
    }

    public void run(){
        System.out.println("waiting for clients to connect");
        try{
            for(int i=0 ; i<10;i++) { //10 clients


                Socket socket = s.accept();


                clientHandler client = new clientHandler(socket);
                this.clients.add(client);
                client.start();

            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}