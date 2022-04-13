package com.urise.webapp;

import java.io.File;

public class MainRecursion {
    public static void main(String[] args) {

        printFilesList(new File("."));
    }

    private static void printFilesList(File dir) {
        if (dir.isDirectory()) {
            System.out.print(dir.getName());
            File[] list = dir.listFiles();
            if (list != null) {
                if (list.length > 0) {
                    System.out.println("/");
                } else {
                    System.out.println();
                }

                for (File file : list) {
                    printFilesList(file);
                }
            } else {
                System.out.println(dir.getName());
            }
        }
    }
}



