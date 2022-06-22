package pars.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class CsvWriter implements LogConsumer {

    private static final long BUFFER_SIZE = 100;

    private final PrintWriter printWriter;
    private long bufferCurrent = 0;

    public CsvWriter(String outputPath, boolean append) throws FileNotFoundException {
        File file = new File(outputPath);
        this.printWriter = new PrintWriter(new FileOutputStream(file, append));
    }

    @Override
    public void accept(String[] strings) {
        String string = String.join(",", strings).trim();
        printWriter.println(string);

        if(bufferCurrent++ >= BUFFER_SIZE) {
            printWriter.flush();
            bufferCurrent = 0;
        }
    }

    @Override
    public void close() {
        printWriter.flush();
        printWriter.close();
    }
}