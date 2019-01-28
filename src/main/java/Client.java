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
        System.out.println(client.getClientPort());
        client.pushObject(client);
        //client.start();
    }

    void pushObject(Client client){
        try(Socket socket = new Socket()){
            socket.connect(new InetSocketAddress("localhost", 8411));
            OutputStream out = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            objectOutputStream.writeObject(client);
            objectOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    void start(List<CommandObject> quiuiList) {
        System.out.println(getClientPort());
    }
}
