package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {


    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);

    }

    @Test
//    @Disabled
    void canGetAllStudents() {
        //when
        underTest.getAllStudents();

        //then
        Mockito.verify(studentRepository).findAll();
    }

    @Test
    void addStudent() {

        Student student = new Student(
                "Jamila",
                "jamila@gmail.com",
                Gender.FEMALE
        );

        //when
        underTest.addStudent(student);

        //then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        Mockito.verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }


    @Test
    void willThrowWhenEmailIsTaken() {

        Student student = new Student(
                "Jamila",
                "jamila@gmail.com",
                Gender.FEMALE
        );

        given(studentRepository.selectExistsEmail(anyString()))
                .willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> underTest.addStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        Mockito.verify(studentRepository, never()).save(any());


    }

    @Test
    @Disabled
    void deleteStudent() {
    }
}