package com.tiy.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by crci1 on 12/20/2016.
 */
public class SendingObjectsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSavingJson() {
        String[] testObjects = new String[]{"clean house", "clean car", "change tire"};
        ThingsToDo thingsToDo = new ThingsToDo(testObjects, false, "Test");
        SendingObjects sendingObjects = new SendingObjects();
        String testJson = sendingObjects.jsonSave(thingsToDo);
        System.out.println(testJson);
        assertEquals("{\"done\":false,\"tasks\":[\"clean house\",\"clean car\",\"change tire\"],\"toDoText\":\"Test\"}",
                testJson);
    }

    @Test
    public void testRestoringJson() {
        String[] testObjects = new String[]{"clean house", "clean car", "change tire"};
        ThingsToDo thingsToDo = new ThingsToDo(testObjects, false, "Test");
        SendingObjects sendingObjects = new SendingObjects();
        String testJson = sendingObjects.jsonSave(thingsToDo);
        System.out.println(testJson);
        ThingsToDo item = sendingObjects.jsonRestore(testJson);
        System.out.println(item);
        assertEquals("{\"done\":false,\"tasks\":[\"clean house\",\"clean car\",\"change tire\"],\"toDoText\":\"Test\"}", testJson);

    }

    @Test
    public void testFileWriting() throws Exception {
        try {
            File jsonFileToText = new File("toDo.txt");
            String[] testObjects = new String[]{"clean house", "clean car", "change tire"};
            ThingsToDo thingsToDo = new ThingsToDo(testObjects, false, "Test");
            SendingObjects sendingObjects = new SendingObjects();
            String testJson = sendingObjects.jsonSave(thingsToDo);
            FileWriter jsonWriteToFile = new FileWriter(jsonFileToText);
            jsonWriteToFile.write(testJson);
            jsonWriteToFile.close();
            assertEquals("{\"done\":false,\"tasks\":[\"clean house\",\"clean car\",\"change tire\"],\"toDoText\":\"Test\"}", testJson);


        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }

    }

    @Test
    public void testReadFileFromJson() throws Exception {
        File jsonFileToText = new File("toDo.txt");
        Scanner scanner = new Scanner(jsonFileToText);
        String readFile = scanner.nextLine();
        SendingObjects sendingObjects = new SendingObjects();
        System.out.println(readFile);
        ThingsToDo thingsToDo = sendingObjects.jsonRestore(readFile);
        System.out.println(thingsToDo);
        assertEquals("Test", thingsToDo.toString());


    }

}