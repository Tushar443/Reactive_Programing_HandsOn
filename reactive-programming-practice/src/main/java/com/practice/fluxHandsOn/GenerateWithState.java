package com.practice.fluxHandsOn;

import com.practice.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

interface FileReaderService {
    Flux<String> read(Path path) throws IOException;
}

class FileReaderServiceImpl implements FileReaderService {
    private static final Logger log = LoggerFactory.getLogger(FileReaderServiceImpl.class);
    private static final Path PATH = Path.of("src/main/resources/FilesDemo");

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


public class GenerateWithState {
    private static final Logger log = LoggerFactory.getLogger(GenerateWithState.class);

    public static void main(String[] args) {
//        generateCountryName();
//        generateOverloadedDemo();

    }

    private static void generateOverloadedDemo() {
        Flux.generate(() -> 0, (counter, sync) -> {
            String country = Util.getFaker().country().name();
            sync.next(country);
            counter++;
            if (counter == 10 || country.equalsIgnoreCase("India")) {
                sync.complete();
            }
            return counter;
        }).subscribe(Util.subscriber());
    }

    // Issue is with state we have to used external variable
    private static void generateCountryName() {
        // requirement is stop before 10 value or if country is india.
        AtomicInteger auAtomicInteger = new AtomicInteger(0);
        Flux.generate(synchronousSink -> {
            String country = Util.getFaker().country().name();
            synchronousSink.next(country);
            auAtomicInteger.incrementAndGet();
            if (auAtomicInteger.get() == 10 || country.equalsIgnoreCase("India")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
