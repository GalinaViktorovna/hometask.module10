package com.hometask.module10;

import com.hometask.module10.cesarcode.CipherType;
import com.hometask.module10.cesarcode.EnglishCesar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileEditor {

    public static String createFileName(String data) throws FilesNameException {
        char[] chars = data.toCharArray();
        for (char c : chars) {
            if (!Character.isAlphabetic(c)) {
                throw new FilesNameException("Wrong name!!!");
            }
        }
        return data.concat(".txt");
    }

    public static String cryptFile(String message, int key, CipherType type) throws EmptyMessageException {
        if (message.isEmpty()) {
            throw new EmptyMessageException("Sorry!Your message is empty! Try again!");
        }
        if (key < 0) {
            throw new IllegalArgumentException("Wrong key!Try again!");
        }
        return EnglishCesar.toCrypt(message, key, type);
    }

    public static void writeFile(File file, String data, int key) throws IOException, EmptyMessageException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(cryptFile(data, key, CipherType.ENCRYPT));
            fileWriter.close();
            System.out.println("Message was encrypt and write in file!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }

        }
    }

    public static List<String> readFile(File file, int key) throws IOException, EmptyMessageException {
        FileReader fileReader = null;
        List<String> data;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            data = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                data.add(cryptFile(line, key, CipherType.DECRYPT));
            }
            bufferedReader.close();
            fileReader.close();
            return data;
        } catch (IOException e) {
            throw new IOException("File don't read!!!");
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }

        }
    }
}
