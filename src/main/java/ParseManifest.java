import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.*;

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

    public void createCommand(String file) {
//        Дописать хранение значений в List<String>
//        не поддеживает создание комманд в манифесте одного типа
        HashMap<String, String> commands = new HashMap<>();
        String[] command = file.split("}\n");
        for (String everyCommand : command) {
            String[] parseCommand = everyCommand.split("\\{");
            commands.put(parseCommand[0], parseCommand[1]);
//            System.out.println(parseCommand[1]);
        }
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            switch (entry.getKey()) {
                case "file":
                    new FileObject(entry.getValue());
                    break;
                case "package":
                    System.out.println("package");
                    break;
                case "command":
                    System.out.println("command");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        ParseManifest pm = new ParseManifest();
        pm.getManifestFile();
    }
}

