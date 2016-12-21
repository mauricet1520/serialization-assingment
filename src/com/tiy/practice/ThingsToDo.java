package com.tiy.practice;

import java.io.Serializable;

public class ThingsToDo implements Serializable {
    private String[] tasks;
    private boolean done;
    private String toDoText;

    public void setDone(boolean done) {
        this.done = done;
    }

    public ThingsToDo(String[] tasks, boolean done, String toDoText) {
        this.tasks = tasks;
        this.done = done;
        this.toDoText = toDoText;
    }

    public String getToDoText() {
        return toDoText;
    }

    public String[] getTasks() {
        return tasks;
    }

    public boolean isDone() {
        return done;
    }

    ThingsToDo() {

    }

    public String toString() {
        if (isDone()) {
            return toDoText + " (done)";
        } else {
            return toDoText;
        }
    }


}
