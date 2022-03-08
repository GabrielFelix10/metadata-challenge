package com.metadata.mock;

import com.metadata.resource.parameters.CourseRegistrationParameter;

public class CourseRegistrationParameterMock {

    public static CourseRegistrationParameter mock() {
        return buildCourseRegistrationParameter();
    }

    private static CourseRegistrationParameter buildCourseRegistrationParameter(){

        return new CourseRegistrationParameter(
                StudentMock.mock().getId(),
                CourseMock.mock().getId());
    }
}
