package pars.writer;

import java.io.FileNotFoundException;

public class CsvErrorWriter extends CsvWriter {

    public CsvErrorWriter(String outputPath, boolean append) throws FileNotFoundException {
        super(outputPath, append);
    }

    @Override
    public void accept(String[] strings) {
        if(strings.length >= 2 && strings[2].equals("ERROR")) {
            super.accept(strings);
        }
    }
}
