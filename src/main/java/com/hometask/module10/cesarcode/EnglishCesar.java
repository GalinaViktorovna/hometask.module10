package com.hometask.module10.cesarcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnglishCesar {

    private final static List<Character> ALPHABET;
    private final static int ALPHABET_SIZE = 26;

    static {
        List<Character> list = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            list.add(c);
        }
        ALPHABET = Collections.unmodifiableList(list);
    }

    public static String toCrypt(String text,int key, CipherType type) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            int index = ALPHABET.indexOf(Character.toUpperCase(c));
            int newIndex = (index + key) % ALPHABET_SIZE;

            switch (type) {
                case ENCRYPT:
                    newIndex = (index + key) % ALPHABET_SIZE;
                    break;
                case DECRYPT:
                    newIndex = ((index - key + ALPHABET_SIZE) % ALPHABET_SIZE);
            }
            if (Character.isUpperCase(c)) {
                output.append(ALPHABET.get(newIndex));
            } else if ((!Character.isAlphabetic(c))) {
                output.append(c);
            } else {
                output.append(Character.toLowerCase(ALPHABET.get(newIndex)));
            }
        }
        return text;
    }
}

