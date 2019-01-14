import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start(){
        try(Socket socketClient = new Socket("127.0.0.1", 8411);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream())))
        {
            writer.write("Hello");
            writer.flush();
        }catch (IOException e){
            e.getStackTrace();
        }
    }
}
