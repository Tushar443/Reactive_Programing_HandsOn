package com.practice.monoHandOn.assignment1;

import com.practice.common.Util;

public class DemoFileService {
    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();
        fileService.write("file.text","This is the way").subscribe(Util.subscriber());

        fileService.read("file.text").subscribe(Util.subscriber());

        fileService.delete("file.text").subscribe(Util.subscriber());
    }
}
