import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class ExecutorCommand {
//    public void execute(String[] command){
//        StringBuilder sb = new StringBuilder();
//        try{
//            Process proc = new ProcessBuilder(command).start();
//            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//            OutputStreamWriter output = new OutputStreamWriter(proc.getOutputStream());
//            String s;
//            while ((s = stdInput.readLine()) != null){
//                System.out.println(s);
//                if (s.contains("Password:")){
//                    System.out.println("pass");
//                }
//                sb.append(s);
//                sb.append("\n");
//
//            }
//
//            while ((s = stdError.readLine()) != null){
//                System.out.println(s);
//                if (s.contains("Password:")){
//                    System.out.println("pass");
//                }
//                sb.append(s);
//                sb.append("\n");
//            }
////            int i = proc.waitFor();
//           // System.out.println(sb.toString());
////            System.out.println(i);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void execute(String[] command) {
        InputStreamReader stdInput;
        InputStreamReader errInput;
        OutputStreamWriter output;
        try {
            Process pb = new ProcessBuilder(command).start();
            output = new OutputStreamWriter(pb.getOutputStream());
            errInput = new InputStreamReader(pb.getErrorStream());
            stdInput = new InputStreamReader(pb.getInputStream());
            int stdBytes, errBytes, tryes = 0;
            char[] stdBuffer = new char[1024];
            char[] errBuffer = new char[1024];
            while ((errBytes = errInput.read(errBuffer, 0, 1024)) != -1) {
                if (errBytes == 0) continue;
                String errData = String.valueOf(errBuffer, 0, errBytes);
                System.out.println(errData);
                if (errData.contains("Password:")) {
                    Scanner inPassw = new Scanner(System.in);
                    output.write(inPassw.nextLine());
                    output.write('\n');
                    output.flush();
                    tryes++;
                }
            }
            while ((stdBytes = stdInput.read(stdBuffer, 0, 1024)) != -1){
                if (stdBytes == 0) continue;
                String stdData = String.valueOf(stdBuffer, 0, stdBytes);
                System.out.println(stdData);
            }
            System.out.println(pb.waitFor());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
