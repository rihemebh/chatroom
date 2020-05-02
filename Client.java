import java.net.*;
import  java.io.*;
public class Client {

    public static void main(String[] args) throws IOException {
        try {
           final  Socket socket = new Socket("localhost", 4999);

            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            final BufferedReader inputClavier = new BufferedReader(new InputStreamReader(System.in));



        Thread envoyer = new Thread(new Runnable() {
            String msg;
            public void run() {
                while (true) {
                    try{
                    msg = inputClavier.readLine();


                    out.writeUTF(msg);
                    out.flush();
                        if(msg.equals("QUIT")) {

                       break;
                        }
                    }
                    catch(IOException e){ e.printStackTrace(); }

            }
        }});

        envoyer.start();

        Thread recevoir = new Thread(new Runnable() {
            String msg;
            public void run() {
                while (true) {
                    try {
                        msg = in.readUTF();
                        System.out.println( msg);
                    } catch(EOFException e){

                        break;
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });

        recevoir.start();

        } catch (IOException e) {
            System.out.println("sorry the server is not available");

        }

    }

}