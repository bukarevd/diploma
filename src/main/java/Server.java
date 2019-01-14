import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        start();
    }

    public static void start() {

        try (ServerSocket server = new ServerSocket(8411)) {
            System.out.println("Server started on " + server);
            while (true)
                try (Socket socket = server.accept();
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String request = reader.readLine();
                    System.out.println(request);
                    writer.write("test " + request.length());
                    writer.flush();
                }catch (IOException e){
                    e.getStackTrace();
                }

        } catch (IOException e) {
            e.getStackTrace();
        }


    }
}
