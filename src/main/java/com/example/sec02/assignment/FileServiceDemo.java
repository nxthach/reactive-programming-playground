package com.example.sec02.assignment;

import com.example.common.Util;

public class FileServiceDemo {
    public static void main(String[] args) {
        FileServiceImpl fileService = new FileServiceImpl();

        fileService.write("file.txt", "This is a test file")
                .subscribe(Util.subscriber());

        System.out.println("---");

        fileService.read("file.txt")
                .subscribe(Util.subscriber());

    }

    static class Demo1 {
        public static void main(String[] args) {
            MyFileServiceImpl fileService = new MyFileServiceImpl();
            fileService.write("text.txt", "hello")
                    .subscribe(Util.subscriber());
        }
    }

    static class Demo2{
        public static void main(String[] args) {
            MyFileServiceImpl fileService = new MyFileServiceImpl();
            fileService.read("text.txt")
                    .subscribe(Util.subscriber());
        }
    }

    static class Demo3{
        public static void main(String[] args) {
            MyFileServiceImpl fileService = new MyFileServiceImpl();
            fileService.delete("text.txt")
                    .subscribe(Util.subscriber());
        }
    }
}
