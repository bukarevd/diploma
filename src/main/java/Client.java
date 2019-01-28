import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class Client extends DimplomaApp implements Serializable {
    private static final String CLIENTCONFIG = Client.class.getClassLoader().getResource("client.conf").getFile();
    private int clientPort;
    private int serverPort;
    private String nameClient;
    private String server;

    public String getCLIENTCONFIG() {
        return CLIENTCONFIG;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public static void main(String[] args) {
        Client client = new Client();
        ParserConfigFiles parserClientFiles = new ParserConfigFiles(client);
        parserClientFiles.getConfig();
        client.pushObject(client);
        //client.start();
    }

    void pushObject(Client client) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(client.getServer(), client.getServerPort()));
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(client);
            objectOutputStream.flush();
        }catch (IOException e){
            System.out.println("Не удалось соединиться с сервером!!!");
        }

        }

        @Override
        void start (List < CommandObject > quiuiList) {
        }
    }
