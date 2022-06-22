package window;

import java.io.*;

public class matrix {

    public int countLines(String filename) throws IOException {
        LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
        int cnt = 0;
        String lineRead = "";
        while ((lineRead = reader.readLine()) != null) {}

        cnt = reader.getLineNumber();
        reader.close();
        return cnt;
    }

    public String Matrix (String str) throws java.lang.Exception {

        FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_fics/csv_templates.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        FileWriter ffw = new FileWriter(new File("matrix.csv"), true);
        PrintWriter pw2 = new PrintWriter(ffw);

        int l = countLines("C:/Users/user/Desktop/Telegram Desktop/window_fics/csv_templates.csv"); //number of lines

        String line = br.readLine();
        String event = "", res = "";
        int [] rez = new int[l];
        int i = 0;

        String [] mass = str.split(",");

        while (line != null) {
                event = line.substring(0,8);
                //System.out.println(event+" - matrix");

                for (int j = 0; j < mass.length; j++) {
                    //System.out.println(mass[j]);
                    if (mass[j].contentEquals(event)) {
                        rez[i] += 1;
                        //System.out.println("equal" + " (count) " + rez[i] + " (mass) " + i);
                    }
                }

                line = br.readLine();
                i++;
        }


        for (int j = 0; j < rez.length; j++){
            res += rez[j]+",";
        }

        //System.out.print(res);
        pw2.write(res+"\n");

        pw2.close();
        //System.out.println("done!");
        return res;
    }
}
