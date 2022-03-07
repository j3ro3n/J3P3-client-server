import java.net.*;
import java.io.*;

public class FileShareServer {

    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;

    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(40000);
            System.out.println("Some one has started the FileSharing server! SushiShare Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }


        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);

                Thread t = new Thread(new ServiceShareClient(clientSocket));

                t.start();

            } catch (Exception e) {
                System.err.println("Error in connection attempt.");
            }
        }
    }
}

