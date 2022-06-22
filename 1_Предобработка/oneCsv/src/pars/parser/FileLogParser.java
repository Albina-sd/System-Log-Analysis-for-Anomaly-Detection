package pars.parser;

import pars.writer.LogConsumer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLogParser {

    private final Pattern pattern;
    private final LogConsumer consumer;

    public FileLogParser(String regexPattern, LogConsumer consumer) {
        this.pattern = Pattern.compile(regexPattern);
        this.consumer = consumer;
    }

    public void parse(String filePath) throws IOException {
        try(FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedInputStream bf = new BufferedInputStream(fileInputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8))) {

            reader.lines()
                    .map(this::parseLine)
                    .filter(i -> i.length > 0)
                    .forEach(consumer);
        }
    }

    private String[] parseLine(String line) {
        Matcher matcher = pattern.matcher(line);

        boolean found = matcher.find();
        if(found) {
            String[] res = new String[matcher.groupCount()];
            for (int i = 0; i < res.length; i++) {
                res[i] = matcher.group(i + 1);
            }
            return res;
        }

        return new String[0];
    }
}
