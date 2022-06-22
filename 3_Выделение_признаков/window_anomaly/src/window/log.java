package window;

import java.io.*;
import java.lang.*;

public class log {

    public static void main(String[] args) throws java.lang.Exception {

        FileInputStream f = new FileInputStream("C:/Users/user/Desktop/Telegram Desktop/window_anomaly/result_7.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(f));

        StringBuilder sb2 = new StringBuilder();

        FileWriter fw2 = new FileWriter(new File("matrix_output_7.csv"), true);
        PrintWriter pw2 = new PrintWriter(fw2);

        String line = br.readLine();
        sb2.append(line+"\n");
        pw2.write(sb2.toString());
        pw2.close();

        templates temp = new templates();
        String a = temp.temp();
        String [] anomaly = a.split(",");

        String mass [] = line.split(",");

        int k = 0, j = 0;
        line = br.readLine();

        while (line != null){
            if (j>0) {
                //System.out.println(line);
                String[] massWin = line.split(","); //считываем строку шаблонов одного окна
                String mW = "";

                for (int i = 0; i < massWin.length; i++) {
                    if (!massWin[i].equals("0")){
                        mW += mass[i]+",";
                    }
                }

                String win [] = mW.split(",");

                int z = 0;
                while ((z < win.length)&&(k == 0)){

                    for (int b = 0; b < anomaly.length; b++) {

                        if (win[z].equals(anomaly[b])){
                            k = 1;
                        }
                    }
                    z++;
                }

                matrix add = new matrix();
                if (k == 0)
                    add.Matrix(",0", line);
                else
                    add.Matrix(",1", line);
            }

            k = 0;
            j++;
            line = br.readLine();
        }

        System.out.println("done!");

    }

}
