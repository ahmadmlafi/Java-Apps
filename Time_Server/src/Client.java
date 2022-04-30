import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) {

        final int port = 5000;
        final String host = "localhost";
        try (Socket socket = new Socket(host, port)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String userInput = null;

            while (!"Exit".equalsIgnoreCase(userInput)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (in.ready()) {
                    System.out.println(in.readLine());
                }
                userInput = scanner.nextLine();
                out.println(userInput);

            }

            scanner.close();
            System.out.println(in.readLine());
        } catch (IOException e) {
            System.out.println("Error: Server is not running...");
        }

    }


}
