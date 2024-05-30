package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Введите ваши данные через пробел Фамилия_Имя_Отчество_Дату рождения в формате dd.MM.yyyy_Номер телефона без знаков_Пол(f или m)");
        String str = getUserString();
        String[] parsedString = parseString(str);
        if (parsedString.length < 6) {
            throw new IllegalArgumentException("Not enough data. Required 6. Yours: " + parsedString.length);
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);

        try {
            format.parse(parsedString[3]);
        } catch (ParseException e) {
            throw new FormatException("Error in birthday " + parsedString[3], e);
        }

        if (!parsedString[4].matches("\\d+")) {
            throw new FormatException("Error in phone number " + parsedString[4]);
        }

        if(!parsedString[5].equals("m") && !parsedString[5].equals("f")) {
            throw new FormatException("Error in sex " + parsedString[5]);
        }

        writeToFile(str, parsedString[0]);
    }

    public static String[] parseString(String str) {
        return str.split(" ");
    }

    public static String getUserString(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку:");
        return scanner.nextLine();
    }

    public static void writeToFile(String str, String fileName) {
        try (FileWriter writer = new FileWriter(fileName + ".txt", true)){
            writer.write(str + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Save failed with error" + e.getMessage());
        }
    }
}