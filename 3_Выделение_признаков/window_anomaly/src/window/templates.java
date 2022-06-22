package window;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class templates {
    public String temp () throws java.lang.Exception {
        FileInputStream f2 = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_anomaly/csv_templates_labeled.csv");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(f2));

        String k = "";
        String anomaly = "", event = "";
        String line2 = br2.readLine();

        while (line2 != null) { //проходим по файлу с шаблонами и аномалиями
            //System.out.println(line2);

            String Regex = "^(.*)\\,(.*)\\,(.*)\\,(.*)";
            Pattern pattern = Pattern.compile(Regex);
            Matcher matcher = pattern.matcher(line2);

            if (matcher.matches()) {
                event = matcher.group(1);
                anomaly = matcher.group(4);

                if ((anomaly.equals("1"))) {
                    k += event + ",";
                }

            } else
                System.out.println("ERROR");

            line2 = br2.readLine();
        }

        br2.close();

        return k;
    }
}
