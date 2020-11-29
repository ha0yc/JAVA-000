package haoyc.assignment_03.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class User {
    private int id;
    private String name;

    private Lesson lesson;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
