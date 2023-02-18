package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void itShouldCheckSelectExistsEmail() {

        //given
        String email = "jamila@gmail.com";
        Student student = new Student(
                "Jamila",
                email,
                Gender.FEMALE
        );

        underTest.save(student);

        //when
        Boolean existsEmail = underTest.selectExistsEmail(email);

        //then
        assertThat(existsEmail).isTrue();

    }
}
