import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Objects;

public class Client extends DimplomaApp implements Serializable {
    private static final String CLIENTCONFIG = Objects.requireNonNull(Client.class.getClassLoader().getResource("client.conf")).getFile();
    private int clientPort;
    private String clientAddress;
    private int serverPort;
    private String nameClient;
    private String server;

    String getCLIENTCONFIG() {
        return CLIENTCONFIG;
    }

    public int getClientPort() {
        return clientPort;
    }

    void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    private int getServerPort() {
        return serverPort;
    }

    void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getNameClient() {
        return nameClient;
    }

    void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    private String getServer() {
        return server;
    }

    void setServer(String server) {
        this.server = server;
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {

            System.out.println(Inet4Address.getLocalHost().);
        } catch (UnknownHostException e) {
            System.out.println("Не удается определить адрес хоста");
        }
        System.out.println(client.getClientAddress());
        ParserConfigFiles parserClientFiles = new ParserConfigFiles(client);
        parserClientFiles.getConfig();
        try (Socket socket = new Socket()) {
            client.pushObject(socket, client);
            client.getCommandObject(socket);
        } catch (IOException e) {
            System.out.println("Не удалось соединиться с сервером!!!");
        }
        //client.start();
    }

    private void pushObject(Socket socket, Client client) throws IOException {

            socket.connect(new InetSocketAddress(client.getServer(), client.getServerPort()));
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(client);
            objectOutputStream.flush();



    }

    private void getCommandObject(Socket socket){
        CommandsObject commandsObject = null;
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            commandsObject = (CommandsObject) in.readObject();
            if(commandsObject instanceof FileObject)
                System.out.println((FileObject) commandsObject);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    void start(List<CommandsObject> quiuiList) {
    }
}
