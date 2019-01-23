import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;

public class FileObject extends CommandObject implements Externalizable {
    String name;
    String path;
    String content;
    String owner;
    String group;
    int chown;

    private static final long serialVersionUID = 0L;
    private static final int VERSION = 0;

    public FileObject(String commandString) {
        parsingCommand(commandString);
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

    public int getChown() {
        return chown;
    }

    public void setChown(int chown) {
        this.chown = chown;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(VERSION);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int version = in.readInt();
        if (version > VERSION) {
            throw new IOException("unsupported version " + version);
        }
    }

    public void parsingCommand(String commandString){
        HashMap<String, String> commandHashMap = new HashMap<>();
        String[] tempString = commandString.split(";\n");
        for (String str: tempString) {
            String[] keyValue = str.split("=>");
            commandHashMap.put(keyValue[0], keyValue[1]);
        }
        setValue(commandHashMap);
    }

    public void setValue(HashMap<String, String> ValuesHashMap){
       setName(ValuesHashMap.get("name"));
       setPath(ValuesHashMap.get("path"));
       setContent(ValuesHashMap.get("content"));
       setOwner(ValuesHashMap.get("owner"));
       setGroup(ValuesHashMap.get("group"));
       setChown(Integer.parseInt(ValuesHashMap.get("chown")));
    }
}
