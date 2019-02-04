import java.io.Serializable;
import java.util.HashMap;

abstract public class CommandsObject implements Serializable {
    public abstract void setValue(HashMap<String, String> valueHashMap);
    public abstract void execute();

}
