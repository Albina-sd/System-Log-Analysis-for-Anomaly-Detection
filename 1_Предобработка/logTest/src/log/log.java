package log;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.*;

public class log {

    public static void main(String[] args) throws java.lang.Exception {

        //"C:/Users/user/Desktop/java/log/src/auth-service.log"
        //"C:/Users/user/Desktop/Telegram Desktop/logs/auth-service/auth-service.log"
        //file walker
        FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/client-service.log");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        FileWriter fw = new FileWriter(new File("module-rule-management.csv"), true);
        PrintWriter pw = new PrintWriter(fw);
        StringBuilder sb = new StringBuilder();
        sb.append("date");
        sb.append(',');
        sb.append("name");
        sb.append(',');
        sb.append("level");
        sb.append(',');
        sb.append("namelog");
        sb.append(',');
        sb.append("message");
        sb.append('\n');

        pw.write(sb.toString());
        pw.flush();

        int j = 0, i = 0;

        String line = br.readLine();
        int t=0;

        while (line != null){
            System.out.println(line);

            String Regex = "^(.*)\\s\\[(.*)\\,.*\\,.*\\]\\s(DEBUG|INFO|WARN|ERROR)\\s+(.*)\\s\\-\\s(.*)";
            Pattern pattern = Pattern.compile(Regex);

            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                System.out.println("+++++++++++++++++++");
                System.out.println(t);
                String data = matcher.group(1);
                String name = matcher.group(2);
                String level = matcher.group(3);
                String namelog = matcher.group(4);
                String message = matcher.group(5);

                String m[] = {data, name, level, namelog, message};
                for (j = 0; j < 5; j++)
                    System.out.print(m[j] + " ");
                System.out.println("\n");

                pw.write(String.join(",", data, name, level, namelog, message, "\n"));
                pw.flush();
            }

            line = br.readLine();
            t++;
        }

        pw.close();
        System.out.println("done!");

    }

}
