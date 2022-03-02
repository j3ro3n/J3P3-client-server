import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class FileShareClient {
        public static int PORT=9443;
        public static int BUFFER_SIZE=8*1024; // ik dacht 4*1024 = 4096kb te verdubbelen naar 8*1024 = 8192kb
    public static void main(String[] args) throws Exception {
        String fileName = null;
        String ip = null;
        try {
            ip = args[0];
            fileName = args[1];
        } catch (Exception e) {
            System.out.println("Voer de bestandsnaam in: "); // één commandline argument nodig
        }

        File file = new File(fileName); // Hier het relatieve pad naar het bestand noteren.
        Socket socket = new Socket(ip, PORT);

        InputStream in = socket.getInputStream();
        OutputStream out = new FileOutputStream("received.zip");

        byte [] buffer = new byte[BUFFER_SIZE];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) > 0) {
            out.write(buffer,0,bytesRead);
        }

        out.close(); // Hier sluit ik de output connectie
        in.close();  // Hier sluit ik de input connectie
        socket.close(); // Hier sluit ik de socket
        System.out.println("FileShare gelukt!");
    }
}