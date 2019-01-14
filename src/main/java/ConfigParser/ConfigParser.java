package ConfigParser;
import java.io.*;
import java.nio.charset.Charset;

public class ConfigParser extends Parser {
    @Override
    public void command(File file) {
            switch (file.getName()){
                case ("server.conf"):
                  new ServerObject(file).readFile();
                  break;
                case ("client.conf"):
                    new ClientObject();
                    break;
            }
    }

    public static void main(String[] args) {

        File server = new File(ConfigParser.class.getClassLoader().getResource("server.conf").getFile());
        System.out.println(server.getAbsoluteFile());

        new ConfigParser().command(server);
    }
}
class ServerObject{
    File file;

    public ServerObject(File file) {
        this.file = file;
    }

    public void readFile(){
        try(InputStream in = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()){
            byte[] buf = new byte [1024];
            int len;
            while ((len = in.read()) > 0){
                byteArrayOutputStream.write(buf, 0, len);
            }
            String str = new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8"));
            System.out.println(str);
        }catch (FileNotFoundException e){
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientObject{

}
