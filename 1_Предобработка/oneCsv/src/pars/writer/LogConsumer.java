package pars.writer;

import java.util.function.Consumer;

public interface LogConsumer extends Consumer<String[]>, AutoCloseable {

    @Override
    void close();

}
