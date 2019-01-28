import java.io.IOException;
import java.io.ObjectInputStream;
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
        System.out.println(server.getSERVERCONFIG());
        ParserConfigFiles serverConfig = new ParserConfigFiles(server);
        serverConfig.getConfig();
        ParseManifest parseManifest = new ParseManifest();
        server.start(parseManifest.getManifestFile());
    }

    @Override
    void start(List<CommandObject> quiuiList) {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            Socket socket = server.accept();
            getMessage(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getMessage(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            Client clientObject = (Client) in.readObject();
            System.out.println(clientObject.getCLIENTCONFIG());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
