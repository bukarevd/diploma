import java.io.*;

public class ExecutorCommand {
    public void execute(String[] command){
        StringBuilder sb = new StringBuilder();
        try{
            Process proc = new ProcessBuilder(command).start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            OutputStreamWriter output = new OutputStreamWriter(proc.getOutputStream());
            String s;
            while ((s = stdInput.readLine()) != null){
                sb.append(s);
                sb.append("\n");
                if (s.contains("password")){
                    System.out.println("password");
                }
            }

            while ((s = stdError.readLine()) != null){
                sb.append(s);
                sb.append("\n");
            }
            int i = proc.waitFor();
            System.out.println(sb.toString());;
            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
