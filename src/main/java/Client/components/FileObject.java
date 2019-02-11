package components;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class FileObject extends CommandsObject implements Externalizable {
    private static final long serialVersionUID = 1L;
    private static final int VERSION = 1;
    private String name = "";
    private String path = "";
    private String content = "";
    private String owner = "";
    private String group = "";
    private String dependency = "";
    private int chmod;
    CommandsObject objectDependecy = null;


    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getPath() {
        return path;
    }

    private void setPath(String path) {
        this.path = path;
    }

    private String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    private String getOwner() {
        return owner;
    }

    private void setOwner(String owner) {
        this.owner = owner;
    }

    private String getGroup() {
        return group;
    }

    private void setGroup(String group) {
        this.group = group;
    }

    public String getDependency() {
        return dependency;
    }

    private void setDependency(String dependency) {
        this.dependency = dependency;
    }

    private int getChmod() {
        return chmod;
    }

    private void setChmod(int chmod) {
        this.chmod = chmod;
    }

    public CommandsObject getObjectDependecy() {
        return objectDependecy;
    }

    public void setObjectDependecy(CommandsObject objectDependecy) {
        this.objectDependecy = objectDependecy;
    }

    public void execute() {
        ExecutorCommand executorCommand = new ExecutorCommand();
        String createFile = "sudo -S echo " + getContent() + " >> " + getPath() + getName();
        String setOwnerGroup = " sudo -S chown -R " + getOwner() + ":" + getGroup() + " " + getPath() + getName();
        String setChmod = " sudo -S chmod " + getChmod() + " " + getPath() + getName();
        String str = createFile + " |" + setOwnerGroup + " |" + setChmod;
        System.out.println(str);
        String[] command = new String[]{"/bin/sh", "-c", str};
        executorCommand.execute(command);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(VERSION);
        out.writeUTF(getName());
        out.writeUTF(getPath());
        out.writeUTF(getContent());
        out.writeUTF(getOwner());
        out.writeUTF(getGroup());
        out.writeUTF(getDependency());
        out.writeInt(getChmod());
        out.writeObject(getObjectDependecy());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int version = in.readInt();
        if (version > VERSION) {
            throw new IOException("Unsupport version FileObject");
        }
        setName(in.readUTF());
        setPath(in.readUTF());
        setContent(in.readUTF());
        setOwner(in.readUTF());
        setGroup(in.readUTF());
        setDependency(in.readUTF());
        setChmod(in.readInt());
        setObjectDependecy((CommandsObject) in.readObject());
    }
}

