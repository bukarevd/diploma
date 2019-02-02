import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class FileObject extends CommandsObject implements Serializable {
    String name;
    String path;
    String content;
    String owner;
    String group;
    String dependence;
    int chmod;

    public FileObject(String commandString) {
        parsingCommand(commandString);
    }

    public FileObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDependence() {
        return dependence;
    }

    public void setDependence(String dependence) {
        this.dependence = dependence;
    }

    public int getChmod() {
        return chmod;
    }

    public void setChmod(int chown) {
        this.chmod = chown;
    }


    public void parsingCommand(String commandString) {
        HashMap<String, String> commandHashMap = new HashMap<>();
        String[] tempString = commandString.split(";\n");
        for (String str : tempString) {
            String[] keyValue = str.split("=>");
            commandHashMap.put(keyValue[0], keyValue[1]);
        }
        System.out.println(Arrays.toString(tempString));
        setValue(commandHashMap);

    }

    @Override
    public void setValue(HashMap<String, String> ValuesHashMap) {
        setName(ValuesHashMap.get("name"));
        setPath(ValuesHashMap.get("path"));
        setContent(ValuesHashMap.get("content"));
        setOwner(ValuesHashMap.get("owner"));
        setGroup(ValuesHashMap.get("group"));
        setDependence(ValuesHashMap.get("dependence"));
        setChmod(Integer.parseInt(ValuesHashMap.get("chown")));
    }

    public void execute(){
        StringBuilder sb = new StringBuilder();
        String[] command = new String[]{"/bin/sh", "-c", "ld"};
        try{
            Process proc = new ProcessBuilder(command).start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null){
                sb.append(s);
                sb.append("\n");
            }

            while ((s = stdError.readLine()) != null){
                sb.append(s);
                sb.append("\n");
            }
            System.out.println(sb.toString());;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
