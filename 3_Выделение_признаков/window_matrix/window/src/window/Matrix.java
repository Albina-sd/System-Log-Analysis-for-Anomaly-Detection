package window;

import java.io.*;

public class Matrix {

    public String matrix_print (String str) throws java.lang.Exception {

        FileInputStream f = new FileInputStream("C:\\Users\\user\\Desktop\\Telegram Desktop\\classifcation\\sliding_win\\5_5\\result_1.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        String line = br.readLine();
        String res = "";

        int i = 0;

        String [] mass = str.split(",");
        String [] event = line.split(",");
        int [] rez  = new int [event.length];

        for (i = 0; i < event.length-1; i++){

            for (int j = 0; j < mass.length; j++) {

                if (mass[j].contentEquals(event[i])) {
                    rez[i] += 1;
                }
            }

            res += rez[i]+",";

        }
        return res;
    }
}
