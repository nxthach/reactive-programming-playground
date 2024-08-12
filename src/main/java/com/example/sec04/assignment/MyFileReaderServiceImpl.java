package com.example.sec04.assignment;

import com.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MyFileReaderServiceImpl implements FileReaderService {

    private static final Logger log = LoggerFactory.getLogger(MyFileReaderServiceImpl.class);

    public static void main(String[] args) {
        Path path = Path.of("src/main/resources/sec04/text.txt");

        MyFileReaderServiceImpl readerService = new MyFileReaderServiceImpl();

        readerService.read(path)
                .subscribe(Util.subscriber());
    }

    @Override
    public Flux<String> read(Path path) {

        return Flux.generate(
                () -> reader(path),
                (reader, sink) -> {

                    String line = readLine(reader);
                    log.info("line data :{}", line);

                    if (line != null) {
                        sink.next(line);
                    } else {
                        sink.complete();
                    }

                    return reader;
                },
                this::readerClose
        );
    }

    private BufferedReader reader(Path path) {
        try {
            return Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readLine(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readerClose(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
