package com.hometask.module10;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class Bootstrap {
    public static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static final String EXIT = "Exit";


    public static void main(String[] args) throws Exception {
        //case 1 : create file
        boolean control = true;
        File file = null;
        while (control) {
            System.out.println("Hello! You want to create file!\n Enter name for your file please" +
                    "(WARNING! Name for file must contain only letters) or \"Exit\" for :");
            String input = READER.readLine();
            try {
                file = new File(FileEditor.createFileName(input));
                if (input.equalsIgnoreCase(EXIT)) {
                    System.exit(1);
                }
                if (file.createNewFile()) {
                    System.out.println("File created successfully!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }


            //case 2 : Encrypt and write in file
            System.out.println("Please enter message for encrypt and write in file" +
                    "and enter key(must be integer!)for encrypt!\n" +
                    "(Delimiter is enter): ");
            try {
                String message = READER.readLine();
                int key = Integer.parseInt(READER.readLine());
                FileEditor.writeFile(file, message, key);

                //case 3: Read and Decrypt file

                List<String> dataFromFile = FileEditor.readFile(file, key);
                System.out.println("Your message from file is: ");
                dataFromFile.forEach(System.out::println);
                control = false;

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}

