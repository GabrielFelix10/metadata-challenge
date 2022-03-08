package com.metadata.service.mapper;

import com.metadata.mock.CourseMock;
import com.metadata.mock.CourseRegistrationParameterMock;
import com.metadata.mock.StudentMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseRegistrationMapperTest {

    private CourseRegistrationMapper mapper = new CourseRegistrationMapper();

    @Test
    public void shouldMapSellerPointParameterToSellerPoint() {
        var parameter = CourseRegistrationParameterMock.mock();

        var courseRegistration = mapper.courseRegistrationParameterToCourseRegistration(StudentMock.mock(), CourseMock.mock());

        assertEquals(Long.valueOf(courseRegistration.getCourse().getId()), parameter.getCourseId());
        assertEquals(Long.valueOf(courseRegistration.getStudent().getId()), parameter.getStudentId());

    }
}
