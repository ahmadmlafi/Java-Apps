import java.net.*;
import java.io.*;

public class Server
{
    private Socket		 socket = null;
    private ServerSocket server = null;
    private DataInputStream in	 = null;

    public static void main(String[] args) throws Exception{
        final int port = 5000;
        ServerSocket server = new ServerSocket(port);

        while(true){
            System.out.println("Server is listening on port " + port + "...");
            Socket socket = server.accept();
            System.out.println("Client accepted");

            System.out.println("Connection established with socket with address: " + socket.getInetAddress() +
                    " and port: " + socket.getPort());

            new ServerThread(socket).start();


        }

    }
}