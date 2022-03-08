package com.metadata.mock;


import com.metadata.model.Course;

public class CourseMock {

    public static Course mock() {
        return buildCourse();
    }

    private static Course buildCourse(){

        return new Course("Clojure");
    }
}