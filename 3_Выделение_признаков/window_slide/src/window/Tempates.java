package window;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tempates {

    public String temp (long defdata, int lwindow) throws java.lang.Exception {
        FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_slide/csv_structured.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        String line = br.readLine();

        String eventId = "", str = "";
        int k = 0;

        while ((line != null)&&(k==0)){
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

                if(matcher2.matches())
                    eventId = matcher2.group(3);
            }

            Date d;
            long time;
            SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy hh:mm:ss");
            d = ft.parse(date);
            time = d.getTime();

            if ((time >= defdata)&&(time <= (defdata + lwindow*1000))){
                str += eventId + ",";
                //System.out.println(date+" "+time+"  "+(time-defdata)+" "+eventId);

                if ((time - defdata) == lwindow*1000)
                    k++;

            }else if ((time - defdata) > lwindow*1000)
                k++;

            line = br.readLine();
        }

        return str;
    }
}
