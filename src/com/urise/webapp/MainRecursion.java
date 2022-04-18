package com.urise.webapp;

import java.io.File;

public class MainRecursion {
    public static void main(String[] args) {

        printFilesList(new File("./src/com/urise/webapp"), "");
    }

    private static void printFilesList(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.print(tab + dir.getName());
            File[] list = dir.listFiles();
            tab = tab + "-";
            if (list != null) {
                if (list.length > 0) {
                    System.out.println("/");
                } else {
                    System.out.println();
                }
                for (File file : list) {
                    printFilesList(file, tab);
                }
            }
        } else {
            System.out.println(tab + dir.getName());
        }
    }
}



