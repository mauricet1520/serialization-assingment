package com.tiy.practice;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by crci1 on 12/20/2016.
 */
public class SendingObjects {
    ThingsToDo toDo = new ThingsToDo();
    ThingsToDo thingsToDo;
    Scanner scanner;
    String[] tasks;

    public static void main(String[] args) {
        try {
            new SendingObjects().test();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void test() throws IOException {

        System.out.println("Would you like to read your original file? Enter: Y our N");
        scanner = new Scanner(System.in);
        String readOldFile = scanner.nextLine();
        if (readOldFile.equalsIgnoreCase("y")) {
            File jsonFileToText = new File("toDo.txt");
            scanner = new Scanner(jsonFileToText);
            String readFile = scanner.nextLine();
            System.out.println(readFile);
            thingsToDo = jsonRestore(readFile);

            System.out.println("Are you done with your task? Enter: Y our N");
            scanner = new Scanner(System.in);
            String doneWithFile = scanner.nextLine();
            if (doneWithFile.equalsIgnoreCase("y")) {
                thingsToDo.setDone(true);
                String savingFileAgain = jsonSave(thingsToDo);
                System.out.println(savingFileAgain);
            } else if (doneWithFile.equalsIgnoreCase("n")) {
                System.out.println(readFile);
            }

        } else if (readOldFile.equalsIgnoreCase("n")) {

            System.out.println("Enter task");
            scanner = new Scanner(System.in);
            String saveTask1 = scanner.nextLine();

            System.out.println("Enter task");
            scanner = new Scanner(System.in);
            String saveTask2 = scanner.nextLine();

            System.out.println("Enter task");
            Scanner scanner = new Scanner(System.in);
            String saveTask3 = scanner.nextLine();

            tasks = new String[]{saveTask1, saveTask2, saveTask3};

            thingsToDo = new ThingsToDo(tasks, false, "Test");

            String myJson = jsonSave(thingsToDo);
            System.out.println(myJson);
            File jsonFileToText = new File("toDo.txt");
            FileWriter jsonWriteToFile = new FileWriter(jsonFileToText);
            jsonWriteToFile.write(myJson);
            jsonWriteToFile.close();
            scanner = new Scanner(jsonFileToText);
            String readFile = scanner.nextLine();
            System.out.println(readFile);

        }

    }

    public String jsonSave(ThingsToDo todoToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(todoToSave);

        return jsonString;
    }

    public ThingsToDo jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        ThingsToDo item = toDoItemParser.parse(jsonTD, ThingsToDo.class);

        return item;
    }

}
