import java.net.*;
import  java.io.*;
//import java.util.*;
public class MyServer{


    public static void main (String [] args) throws IOException{

        ServerSocket s = new ServerSocket(4999);
        Server sev = new Server(s);

        sev.start();
}}

