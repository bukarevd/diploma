import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class CommandObject extends CommandsObject implements Serializable {
    String name;
    String exec;

    public CommandObject(String commandString){
        parsingCommand(commandString);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExec() {
        return exec;
    }

    public void setExec(String exec) {
        this.exec = exec;
    }

    public void parsingCommand(String commandString) {
        HashMap<String, String> commandHashMap = new HashMap<>();
        String[] tempString = commandString.split(";\n");
        System.out.println(Arrays.toString(tempString));
        for (String str : tempString) {
            String[] keyValue = str.split("=>");
            commandHashMap.put(keyValue[0], keyValue[1]);
        }
        setValue(commandHashMap);
    }

    @Override
    public void setValue(HashMap<String, String> ValuesHashMap) {
        setName(ValuesHashMap.get("name"));
        setExec(ValuesHashMap.get("exec"));
    }
}
