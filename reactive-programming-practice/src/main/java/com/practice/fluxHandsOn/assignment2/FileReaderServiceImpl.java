package com.practice.fluxHandsOn.assignment2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

class FileReaderServiceImpl implements FileReaderService {
    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    private static BufferedReader openFile(Path path) throws IOException {
        log.info("opening a file");
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        String line = null;
        try {
            line = reader.readLine();
            log.info("reading line {} ", line);
            if (Objects.isNull(line)) {
                sink.complete();
            } else {
                sink.next(line);
            }
        } catch (IOException e) {
            sink.error(e);
        }
        return reader;
    }

    private void closeFile(BufferedReader reader) {
        try {
            reader.close();
            log.info("File is Closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flux<String> read(Path path) throws IOException {
        return Flux.generate(() -> openFile(path), this::readFile, this::closeFile);
    }
}
