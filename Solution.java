package com.javarush.task.task18.task1808;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/* 
Разделение файла
*/

public class Solution {
    public static String fromFile;
    public static String toFile1;
    public static String toFile2;

    public static void main(String[] args) {
        fromFile = readFileName();
        toFile1 = readFileName();
        toFile2 = readFileName();
        divideFile(fromFile, toFile1, toFile2);
    }

    public static String readFileName() {
        return new Scanner(System.in).nextLine();
    }

    public static void divideFile(String from, String to1, String to2) {
        byte[] firstFileBytes;
        byte[] secondFileBytes;

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(from);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (fileInputStream.available() > 0) {
                byte[] bytes = new byte[fileInputStream.available()];
                int count = fileInputStream.read(bytes);

                int tempCount = count / 2;

                if (count % 2 == 0) {
                    firstFileBytes = Arrays.copyOfRange(bytes, 0, tempCount);
                    secondFileBytes = Arrays.copyOfRange(bytes, tempCount, bytes.length);
                } else {
                    firstFileBytes = Arrays.copyOfRange(bytes, 0, tempCount + 1);
                    secondFileBytes = Arrays.copyOfRange(bytes, tempCount + 1, bytes.length);
                }

                printBytes(firstFileBytes, to1);
                printBytes(secondFileBytes, to2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printBytes(byte[] bytes, String to) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(to)){
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
