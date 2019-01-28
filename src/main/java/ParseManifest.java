import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.*;

public class ParseManifest {
    List<CommandObject> commandObjectList = new ArrayList<>();
    private String manifestFileName = ParserConfigFiles.class.getClassLoader().getResource("work.manifest").getFile();
    File manifestFile = new File(manifestFileName);


    public List<CommandObject> getManifestFile() {
        try (InputStream in = new FileInputStream(manifestFile);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) > 0) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            createCommand(new String(byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.getMessage();
        }
        return commandObjectList;
    }

    public void createCommand(String file) {
//        Дописать хранение значений в List<String>
//        не поддеживает создание комманд в манифесте одного типа

        HashMap<String, String> commands = new HashMap<>();
        String[] command = file.split("}\n");
        for (String everyCommand : command) {
            String[] parseCommand = everyCommand.split("\\{");
            commands.put(parseCommand[0], parseCommand[1]);
        }
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            switch (entry.getKey()) {
                case "file":
                    commandObjectList.add(new FileObject(entry.getValue()));
                    break;
                case "package":
                    commandObjectList.add(new PackageObject(entry.getValue()));
                    break;
                case "command":
                    System.out.println("command");
                    break;
            }
        }
    }
}

