
public class Server extends DimplomaApp{
    private static final String SERVERCONFIG = Server.class.getClassLoader().getResource("server.conf").getFile();
    private int serverPort;
    private static Server instances;

    private Server(){}

    public static Server getInstance(){
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
        server.start();
    }

    void start(){
        System.out.println(getServerPort());
    }
}
