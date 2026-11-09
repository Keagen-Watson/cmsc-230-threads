import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// counts every line from every client; COUNT answers with the count so far
public class CountServer {
    static int count;

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(6230)) {
            System.out.println("waiting on port 6230");
            while (true) {
                Socket client = server.accept();
                new Thread(() -> serve(client)).start();
            }
        }
    }

    // counts one client's lines until it leaves
    static void serve(Socket client) {
        try (Scanner in = new Scanner(client.getInputStream());
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.equals("COUNT")) {
                    out.println(count);
                } else {
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("a client failed: " + e.getMessage());
        }
    }

    static synchronized void add() {

    }
}
