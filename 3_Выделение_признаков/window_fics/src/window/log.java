package window;

import window.matrix;

import java.io.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class log {

    public static void main(String[] args) throws java.lang.Exception {

        FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_fics/csv_structured.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        FileWriter fw = new FileWriter(new File("windowFics.csv"), true);
        PrintWriter pw = new PrintWriter(fw);
        StringBuilder sb = new StringBuilder();
        sb.append("eventId\n");
        pw.write(sb.toString());
        pw.flush();

        FileWriter fw2 = new FileWriter(new File("matrix.csv"), true);
        PrintWriter pw2 = new PrintWriter(fw2);
        StringBuilder sb2 = new StringBuilder();

        FileInputStream f2 = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_fics/csv_templates.csv");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(f2));

        String line = br2.readLine();
        String eventId = "";
        while (line != null){
            eventId = line.substring(0,8);
            //System.out.println(event);
            sb2.append(eventId+",");
            line = br2.readLine();
        }
        sb2.append("\n");
        pw2.write(sb2.toString());
        pw2.close();

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер окна(сек)");
        int lwindow = scan.nextInt();

        line = br.readLine();

        String Mstr = "", r = "";
        long m = 0;
        int k = 0, i = 0, dt = 0;

        while (line != null){
            //System.out.println(line);

            String Regex = "^(.*)\\,(.*)\\,(.*)\\,(DEBUG|INFO|WARN|ERROR)\\,(.*)$";
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(line);

            String date = "";

            if (matcher.matches()) {
                date = matcher.group(2);

                eventId = matcher.group(5);

                String Regex2 = "^(.*)\\,(.*)\\,((\\d|\\w)*)\\,(.*)$";
                Pattern pattern2 = Pattern.compile(Regex2);
                Matcher matcher2 = pattern2.matcher(eventId);

                //System.out.println(eventId+" - before");
                if(matcher2.matches()){
                    eventId = matcher2.group(3);
                    System.out.println(eventId+" - window");
                }
            }

            Date d;
            long time;
            SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy hh:mm:ss");
            d = ft.parse(date);
            time = d.getTime();
            System.out.println(date);

            if (k == 0){
                m = time;
            }

            System.out.println("time = "+time+"  m = "+m+" "+(time-m));
            System.out.println((time - m)/60000+" - в мин");

            if ((time >= m)&&(time <= (m+lwindow*1000))){
                Mstr += eventId + ",";

            }else if (((time - m) > lwindow*1000)||((time - m)<0))
                k = 0;

            if ((k == 0)&&(i != 0)){
                System.out.println(Mstr+"k == 0");

                matrix mvect = new matrix();
                r = mvect.Matrix(Mstr);
                System.out.println(r);

                pw.write(Mstr+"\n");
                pw.flush();

                m = time;

                Mstr = eventId + ",";
                System.out.println(Mstr);
            }

            line = br.readLine();
            k++;
            i++;
        }

        System.out.println(Mstr);
        matrix mvect = new matrix();
        r = mvect.Matrix(Mstr);
        System.out.println(r);

        pw.write(Mstr);
        pw.close();
        System.out.println("done!");

    }
}
