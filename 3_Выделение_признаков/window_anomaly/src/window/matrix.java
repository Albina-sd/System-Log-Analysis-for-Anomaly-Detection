package window;

import java.io.*;

public class matrix {

    public void Matrix (String str, String line) throws java.lang.Exception {

        FileWriter fw = new FileWriter(new File("matrix_output_7.csv"), true);
        PrintWriter pw = new PrintWriter(fw);

        pw.write(line+str+"\n");
        pw.close();
    }
}
