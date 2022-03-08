package com.metadata.mock;

import com.metadata.model.Student;

public class StudentMock {

    public static Student mock() {
        return buildStudent();
    }

    private static Student buildStudent(){

        return new Student ("Pelé");
    }
}
