package pars.parser;

import pars.writer.LogConsumer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryLogParser extends FileLogParser {

    private static final Logger log = Logger.getLogger(DirectoryLogParser.class.getName());

    public DirectoryLogParser(String regexPattern, LogConsumer consumer) {
        super(regexPattern, consumer);
    }

    @Override
    public void parse(String dirPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            List<String> files = paths.filter(Files::isRegularFile)
                    .map(Path::toString)
                    .collect(Collectors.toList());

            for(int i=0; i<files.size(); i++) {
                String file = files.get(i);
                log.info("Parsing file: " + file + " (" + (i+1) + "|" + files.size() + ")");
                super.parse(file);
            }
        }
    }
}