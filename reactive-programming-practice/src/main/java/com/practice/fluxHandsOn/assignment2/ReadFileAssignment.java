package com.practice.fluxHandsOn.assignment2;

import com.practice.common.Util;

import java.io.IOException;
import java.nio.file.Path;

public class ReadFileAssignment {
    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/FilesDemo/file.txt"; //resources/FilesDemo/file.txt
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(Path.of(path))
//                .take(6)
                .takeUntil(s->s.equalsIgnoreCase("line17"))
                .subscribe(Util.subscriber());
    }
}
