import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ParseManifest {
    List<CommandsObject> commandObjectList = new ArrayList<>();
    private String manifestFileName = ParserConfigFiles.class.getClassLoader().getResource("work.manifest").getFile();
    File manifestFile = new File(manifestFileName);


    public List<CommandsObject> getManifestFile() {
//        Чтение файла манифеста
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
    //Парсин и добавление объекта команды в List

        String[] command = file.split("}\n");
        System.out.println(Arrays.toString(command));
        for (String everyCommand : command) {
            String[] parseCommand = everyCommand.split("\\{\n");
            switch (parseCommand[0]) {
                case "file":
                    commandObjectList.add(new FileObject(parseCommand[1]));
                    break;
                case "package":
                    commandObjectList.add(new PackageObject(parseCommand[1]));
                    break;
                case "command":
                    commandObjectList.add(new CommandObject(parseCommand[1]));
                    break;
            }
        }

        System.out.println(commandObjectList);
    }
}

