package pars.parser;

import pars.parser.DirectoryLogParser;
import pars.writer.CsvErrorWriter;
import pars.writer.CsvWriter;
import pars.writer.LogConsumer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws java.lang.Exception {
        String regexp = "^(.*)\\s\\[(.*)\\,.*\\,.*\\]\\s(DEBUG|INFO|WARN|ERROR)\\s+(.*)\\s\\-\\s(.*)$";
        String filePath = "C:\\Users\\user\\Desktop\\Telegram Desktop\\log";

        try(LogConsumer consumer = new CsvWriter("out_all_testing.csv", false)) {
            DirectoryLogParser fileLogParser = new DirectoryLogParser(regexp, consumer);
            fileLogParser.parse(filePath);
        }
    }

}
