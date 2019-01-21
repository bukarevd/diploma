public class Client extends DimplomaApp {
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
        client.start();
    }

    @Override
    void start() {
        System.out.println(getClientPort());
    }
}
