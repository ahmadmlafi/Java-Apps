import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerThread extends Thread {

    Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

        BufferedReader in = null;
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String input = "";

            while (!input.equalsIgnoreCase("Exit")) {
                try {
                    sendTime(out);
                    out.println("Enter 'Exit' to end connection or 'time' to resend the date: ");
                    input = in.readLine();
                } catch (IOException i) {
                    System.out.println(i);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                out.println("Connection Closed");
                System.out.println("Connection closed with the socket has address: " + socket.getInetAddress() +
                        " and port: " + socket.getPort());
                if (socket != null) socket.close();
                in.close();
                out.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void sendTime(PrintWriter out) {

        LocalDateTime date = LocalDateTime.now();
        out.println("Current server date: " + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(date));
    }
}