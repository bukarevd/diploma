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
            Client clientObject = getMessage(socket);
            sendMessage(socket, clientObject, quiuiList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //  Получение объекта Client
    Client getMessage(Socket socket) {
        Client clientObject = null;
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            clientObject = (Client) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clientObject;
    }

    //    Отправка объектов на клиент для выполнения
    void sendMessage(Socket socket, Client clientObject, List<CommandsObject> quiuiList) {
        try {
            ObjectOutputStream outServer = new ObjectOutputStream(socket.getOutputStream());
            FileObject fo = new FileObject();
            fo = (FileObject) quiuiList.get(0);
            System.out.println(fo.getName());
            outServer.writeObject(quiuiList.get(0));
            outServer.flush();
//            for (CommandObject command : quiuiList) {
//                outServer.writeObject(command);
//                outServer.flush();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
