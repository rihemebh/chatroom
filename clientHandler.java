import java.net.*;
import  java.io.*;
import java.util.*;


public class clientHandler extends Thread{

    final private Socket s;
    private DataInputStream inputClient;
    private DataOutputStream outputClient;


    public String username;

    clientHandler(Socket s ){

        this.s=s;

        try{
            this.inputClient= new DataInputStream(s.getInputStream());
            this.outputClient=new DataOutputStream(s.getOutputStream());}
        catch(IOException e ){e.printStackTrace();}

    }

    public void run() {

        try {
            send("tap your username to start ");
            String str1 = inputClient.readUTF();
            this.username=str1;
            System.out.println(str1 + " connected");
            if(Server.getClients().size()>1){
            for ( clientHandler client : Server.getClients()){
                if(client!=this) {
                    send(client.username + " is connected");
                    client.send(this.username + " is connected");
                }
            }}else send("no one is connected ");


            BufferedReader inputServer = new BufferedReader(new InputStreamReader(System.in));


            while (!(str1.equals("QUIT"))) {

                str1 = inputClient.readUTF();
                if ((str1.equals("QUIT"))) {
                    outputClient.writeUTF("Quiting...");

                    System.out.println( this.username+" is disconnected");
                    for ( clientHandler client : Server.getClients()){

                        if(client!=this)
                        client.send(this.username + " is disconnected");
                    }

                    Server.getClients().remove(this);

                }else {
                    for ( clientHandler client : Server.getClients()){
                        if(client!=this)
                         client.send(this.username + ">" + str1);}
                }



            }

        }catch(IOException e ){ e.printStackTrace();}
        try{ this.s.close();}
        catch(IOException e )
        {e.printStackTrace();}
    }

    public void send (String msg ) throws IOException{
        outputClient.writeUTF(msg);
    }
}
