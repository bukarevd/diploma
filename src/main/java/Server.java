import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends DimplomaApp {
    private static final String SERVERCONFIG = Server.class.getClassLoader().getResource("server.conf").getFile();
    private int serverPort;
    private static Server instances;


    private Server() {
    }

    public static Server getInstance() {
        if (instances == null) instances = new Server();
        return instances;
    }

    public String getSERVERCONFIG() {
        return SERVERCONFIG;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {
        Server server = getInstance();
        ParserConfigFiles serverConfig = new ParserConfigFiles(server);
        serverConfig.getConfig();
        ParseManifest parseManifest = new ParseManifest();
        server.start(parseManifest.getManifestFile());
    }

    @Override
    void start(List<CommandsObject> quiuiList) {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            Socket socket = server.accept();
            //  Client clientObject = getMessage(socket);
            sendMessage(socket, quiuiList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //  Получение объекта Client
    Client getMessage(Socket socket) {
        Client clientObject = null;
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            //clientObject = (Client) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientObject;
    }

    //    Отправка объектов на клиент для выполнения
    void sendMessage(Socket socket, List<CommandsObject> quiuiList) {
        try {
            while (!socket.isClosed()) {
                ObjectOutputStream outServer = new ObjectOutputStream(socket.getOutputStream());
                for (CommandsObject command : quiuiList) {
                    outServer.writeObject(command);
                    System.out.println(command);
                    outServer.flush();
                }
                outServer.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
