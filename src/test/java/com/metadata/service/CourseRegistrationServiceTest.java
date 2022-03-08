package com.metadata.service;

import com.metadata.exception.CourseExceedLimitException;
import com.metadata.exception.RecordAlreadyExist;
import com.metadata.exception.RecordNotExist;
import com.metadata.exception.StudentsExceedLimitException;
import com.metadata.mock.CourseMock;
import com.metadata.mock.CourseRegistrationMock;
import com.metadata.mock.CourseRegistrationParameterMock;
import com.metadata.mock.StudentMock;
import com.metadata.repository.CourseRegistrationRepository;
import com.metadata.repository.CourseRepository;
import com.metadata.repository.StudentRepository;
import com.metadata.service.mapper.CourseRegistrationMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
public class CourseRegistrationServiceTest {

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRegistrationMapper mapper;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseRegistrationService courseRegistrationService;

    @Before
    public void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldAssociateCorrectAndReturnCourseRegistrationId(){
        var parameter = CourseRegistrationParameterMock.mock();
        var courseRegistration = CourseRegistrationMock.mock(StudentMock.mock(), CourseMock.mock());
        var student = StudentMock.mock();
        var course = CourseMock.mock();

        when(studentRepository.findById(parameter.getStudentId())).thenReturn(Optional.of(student));

        when(courseRepository.findById(parameter.getCourseId())).thenReturn(Optional.of(course));

        when(mapper.courseRegistrationParameterToCourseRegistration(any(), any())).thenReturn(courseRegistration);

        when(courseRegistrationRepository.findByStudentAndCourse(any(), any())).thenReturn(Optional.empty());

        when(courseRegistrationRepository.countByStudentId(any())).thenReturn(0l);

        when(courseRegistrationRepository.countByCourse(any())).thenReturn(0l);

        when(courseRegistrationRepository.save(courseRegistration)).thenReturn(courseRegistration);

        var result = courseRegistrationService.associateStudentToCourse(parameter);

        assertEquals(String.valueOf(courseRegistration.getId()), result.get());
    }

    @Test(expected = CourseExceedLimitException.class)
    public void shouldNotAssociateWhenCourseExceedLimit(){
        var parameter = CourseRegistrationParameterMock.mock();
        var courseRegistration = CourseRegistrationMock.mock(StudentMock.mock(), CourseMock.mock());
        var student = StudentMock.mock();
        var course = CourseMock.mock();

        when(studentRepository.findById(parameter.getStudentId())).thenReturn(Optional.of(student));

        when(courseRepository.findById(parameter.getCourseId())).thenReturn(Optional.of(course));

        when(mapper.courseRegistrationParameterToCourseRegistration(student, course)).thenReturn(courseRegistration);

        when(courseRegistrationRepository.findByStudentAndCourse(student, course))
                .thenReturn(Optional.of(courseRegistration));

        when(courseRegistrationRepository.countByStudentId(any())).thenReturn(6l);

        courseRegistrationService.associateStudentToCourse(parameter);

    }

    @Test(expected = StudentsExceedLimitException.class)
    public void shouldNotAssociateWhenStudentExceedLimit(){
        var parameter = CourseRegistrationParameterMock.mock();
        var courseRegistration = CourseRegistrationMock.mock(StudentMock.mock(), CourseMock.mock());
        var student = StudentMock.mock();
        var course = CourseMock.mock();

        when(studentRepository.findById(parameter.getStudentId())).thenReturn(Optional.of(student));

        when(courseRepository.findById(parameter.getCourseId())).thenReturn(Optional.of(course));

        when(mapper.courseRegistrationParameterToCourseRegistration(student, course)).thenReturn(courseRegistration);

        when(courseRegistrationRepository.findByStudentAndCourse(student, course))
                .thenReturn(Optional.of(courseRegistration));

        when(courseRegistrationRepository.countByStudentId(any())).thenReturn(0l);

        when(courseRegistrationRepository.countByCourse(any())).thenReturn(51l);

        courseRegistrationService.associateStudentToCourse(parameter);

    }

    @Test(expected = RecordAlreadyExist.class)
    public void shouldThrowCourseExistent(){
        var parameter = CourseRegistrationParameterMock.mock();
        var courseRegistration = CourseRegistrationMock.mock(StudentMock.mock(), CourseMock.mock());
        var student = StudentMock.mock();
        var course = CourseMock.mock();

        when(studentRepository.findById(parameter.getStudentId())).thenReturn(Optional.of(student));

        when(courseRepository.findById(parameter.getCourseId())).thenReturn(Optional.of(course));

        when(mapper.courseRegistrationParameterToCourseRegistration(student, course)).thenReturn(courseRegistration);

        when(courseRegistrationRepository.findByStudentAndCourse(student, course))
                .thenReturn(Optional.of(courseRegistration));

        courseRegistrationService.associateStudentToCourse(parameter);

    }

    @Test(expected = RecordNotExist.class)
    public void shouldThrowRecordNotExist(){
        var parameter = CourseRegistrationParameterMock.mock();

        when(studentRepository.findById(parameter.getStudentId())).thenReturn(Optional.empty());


        courseRegistrationService.associateStudentToCourse(parameter);

    }
}
