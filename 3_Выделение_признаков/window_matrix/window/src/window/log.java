package window;

import java.io.*;
import java.lang.*;

public class log {

    public static void main(String[] args) throws java.lang.Exception {

        FileInputStream fl = new FileInputStream("C:\\Users\\user\\Desktop\\Telegram Desktop\\classifcation\\sliding_win\\5_5\\result_1.csv");
        BufferedReader b = new BufferedReader(new InputStreamReader(fl));
        String line = b.readLine();
        String head = line+"\n";

        System.out.println(head);

        FileInputStream f2 = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_matrix/csv_templates_labeled.csv");
        BufferedReader b2 = new BufferedReader(new InputStreamReader(f2));
        line = b2.readLine();
        String str = "";
        while (line != null){
            if (line.substring(line.length()-1).equals("1"))
                str += line.substring(0,8)+",";

            line = b2.readLine();
        }
        System.out.println(str);
        String [] Anomaly = str.split(",");

        for (int lwindow = 6; lwindow <= 6; lwindow++){

            FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/classifcation/window/2/windowLog_"+lwindow+".csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(f));

            FileWriter fw2 = new FileWriter(new File("matrix_"+lwindow+".csv"));
            PrintWriter pw2 = new PrintWriter(fw2);
            pw2.write(head);
            pw2.flush();
            System.out.println("matrix_"+lwindow+".csv");

            int i = 0, k = 0;
            line = br.readLine();

            while (line != null){

                String mass [] = line.split(",");
                i = 0;
                while (i < Anomaly.length) {

                    for (int j = 0; j < mass.length; j++) {
                        if (Anomaly[i].equals(mass[j])){
                            k = 1;
                        }
                    }
                    i++;
                }

                Matrix m = new Matrix();
                String mstr = m.matrix_print(line);

                pw2.write(mstr+k+"\n");
                pw2.flush();
                /*if (k==1)
                    System.out.println(line);*/

                k = 0;
                line = br.readLine();
            }
            pw2.close();
        }
        System.out.println("done!");
    }
}
