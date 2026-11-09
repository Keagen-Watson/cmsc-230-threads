import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// accepts one client, answers its lines, and ends
public class OnceServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(6230);
             Socket client = server.accept();
             Scanner in = new Scanner(client.getInputStream());
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (in.hasNextLine()) {
                out.println("echo: " + in.nextLine());
            }
        }
    }
}