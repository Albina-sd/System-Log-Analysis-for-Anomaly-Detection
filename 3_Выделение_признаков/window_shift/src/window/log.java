package window;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.io.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class log {

    private static CircularFifoQueue coll = new CircularFifoQueue();

    public static void main(String[] args) throws java.lang.Exception {

        int lwindow = 6;

        for (int lshift = 1; lshift <= lwindow; lshift++) {

            coll = new CircularFifoQueue(lwindow);

            FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window/csv_structured.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(f));

            FileWriter fw = new FileWriter(new File("windowLog_"+lshift+".csv"), true);
            PrintWriter pw = new PrintWriter(fw);

            String line = br.readLine();
            System.out.println();

            String mass[] = new String[lwindow];

            int k = 0, i = 0, c = 0;

            while (line != null) {
                //System.out.println(line);
                String res = "";

                String Regex = "^(.*)\\,(.*)\\,(.*)\\,(DEBUG|INFO|WARN|ERROR)\\,(.*)$";
                Pattern pattern = Pattern.compile(Regex);

                Matcher matcher = pattern.matcher(line);

                String eventId = "";

                if (matcher.matches()) {
                    //System.out.println("+++++++++++++++++++");
                    eventId = matcher.group(5);

                    String Regex2 = "^(.*)\\,(.*)\\,((\\d|\\w)*)\\,(.*)$";
                    Pattern pattern2 = Pattern.compile(Regex2);
                    Matcher matcher2 = pattern2.matcher(eventId);

                    //System.out.println(eventId+" - before");
                    if (matcher2.matches()) {
                        eventId = matcher2.group(3);
                        //System.out.println(eventId + " - window");
                    }
                }

                coll.add(eventId);

                if (coll.isAtFullCapacity()) {
                    c++;

                    if (c == lshift) {
                        res = coll.toString();
                        res = res.substring(1, res.length() - 1);
                        res = res.replace(" ", "");

                        //System.out.println("res    " + res);
                        pw.write(res + "\n");
                        pw.flush();

                        c = 0;
                    }
                }

                line = br.readLine();
                k++;
            }
            pw.close();
        }

        System.out.println("done!");
    }
}
