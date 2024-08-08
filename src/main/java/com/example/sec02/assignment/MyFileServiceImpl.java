package com.example.sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class MyFileServiceImpl implements FileService{

    private static final Logger log = LoggerFactory.getLogger(MyFileServiceImpl.class);

    private static final String BASE_PATH = "src/main/resources/sec02/%s";

    public Mono<String> read(String fileName) {
        return Mono.fromSupplier(() -> readFile(fileName));
    }

    private String readFile(String fileName) {
        File file = new File(BASE_PATH.formatted(fileName));

        try (var reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            if (Objects.nonNull(line)) {
                return line;
            }

        } catch (Exception e) {
            log.error("error", e);
        }

        return "";
    }


    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> writeFile(fileName, content));
    }

    private void writeFile(String fileName, String content) {
        File file = new File(BASE_PATH.formatted(fileName));

        try (var writer = new BufferedWriter(new FileWriter(file))) {

            writer.write(content);

        } catch (Exception e) {
            log.error("error", e);
        }
    }

    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));

    }

    private void deleteFile(String fileName) {
        try {
            Files.deleteIfExists(Path.of(BASE_PATH.formatted(fileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
