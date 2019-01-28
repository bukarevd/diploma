import java.io.Serializable;
import java.util.HashMap;

public class PackageObject extends CommandObject implements Serializable {
    String name;
    String version;
    String dependence;

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setDependence(String dependence) {
        this.dependence = dependence;
    }

    public PackageObject(String commandString) {
        parsingCommand(commandString);
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

    @Override
    public void setValue(HashMap<String, String> ValuesHashMap){
        setName(ValuesHashMap.get("name"));
        setVersion(ValuesHashMap.get("version"));
        setDependence(ValuesHashMap.get("dependence"));
    }
}
