package window;

import java.io.*;
import java.lang.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class log {

    public static void main(String[] args) throws java.lang.Exception {

        FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_slide/csv_structured.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        FileWriter fw = new FileWriter(new File("windowSlide.csv"), true);
        PrintWriter pw = new PrintWriter(fw);
        StringBuilder sb = new StringBuilder();
        sb.append("eventId\n");
        pw.write(sb.toString());
        pw.flush();

        FileWriter fw2 = new FileWriter(new File("matrix.csv"), true);
        PrintWriter pw2 = new PrintWriter(fw2);
        StringBuilder sb2 = new StringBuilder();

        FileInputStream f2 = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_slide/csv_templates.csv");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(f2));

        String line = br2.readLine();
        String eventId;
        //записываем id шаблонов в первую строку матрицы
        while (line != null){
            eventId = line.substring(0,8);
            //System.out.println(event);
            sb2.append(eventId+",");
            line = br2.readLine();
        }
        sb2.append("\n");
        pw2.write(sb2.toString());
        pw2.close();

        String Mstr = " ";
        String date = "", dt = "";
        long MaxTime = 0;
        long time;
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy hh:mm:ss");

        line = br.readLine();
        String Regex = "^(.*)\\,(.*)\\,(.*)\\,(DEBUG|INFO|WARN|ERROR)\\,(.*)$";
        Pattern pattern = Pattern.compile(Regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            date = matcher.group(2); //считываем первую дату
        }

        System.out.println("wait");

        //узнаем последнюю дату в файле
        while (line != null){
            matcher = pattern.matcher(line);
            if (matcher.matches()) {
                dt = matcher.group(2);
            }
            Date t;
            t = ft.parse(dt);
            time = t.getTime();

            if (MaxTime < time)
                MaxTime = time;

            line = br.readLine();
        }
        System.out.println("MaxTime = "+MaxTime);

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите размер окна(сек)");
        int lwindow = scan.nextInt();
        System.out.print("Введите размер сдвига(сек)");
        int sdvig = scan.nextInt();
        System.out.println();

        Date d;
        d = ft.parse(date);
        time = d.getTime();

            while (time <= MaxTime) {
                //System.out.println(time + " - new win");
                Tempates tp = new Tempates();
                Mstr = tp.temp(time, lwindow);

                if (!Mstr.isEmpty()) {
                    pw.write(Mstr + "\n");
                    pw.flush();
                    matrix mvect = new matrix(); //формируем вектор событий в полученном окне
                    mvect.Matrix(Mstr);
                }

                time += sdvig*1000;
            }

        pw.close();
        System.out.println("done!");
    }

}
