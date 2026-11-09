import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 *
 * @author Keagen Watson
 */
public class ThreadedEchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(6230)) {
            System.out.println("waiting on port 6230");
            while (true) {
                Socket client = server.accept();
                System.out.println("a client connected");
                new Thread(() -> serve(client)).start();
                System.out.println("the client left");
            }
        }
    }

    // talks to one client until it leaves
    static void serve(Socket client) {
        try (Scanner in = new Scanner(client.getInputStream());
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                out.println(Calculator.answer(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
