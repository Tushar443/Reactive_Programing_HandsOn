package com.practice.fluxHandsOn.assignment2;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;

interface FileReaderService {
    Flux<String> read(Path path) throws IOException;
}
