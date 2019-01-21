import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ParseManifest {

    private String manifestFileName = ParserConfigFiles.class.getClassLoader().getResource("work.manifest").getFile();
    File manifestFile = new File(manifestFileName);



    public void getManifestFile() {
        try (InputStream in = new FileInputStream(manifestFile);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) > 0) {
                byteArrayOutputStream.write(buf, 0, len);
            }
//            System.out.println(new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8")));
            createCommand(new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void createCommand(String file){
        String[] command = file.split("}\n");
        System.out.println(Arrays.toString(command));
    }

    public static void main(String[] args) {
        ParseManifest pm = new ParseManifest();
        pm.getManifestFile();
    }
}

