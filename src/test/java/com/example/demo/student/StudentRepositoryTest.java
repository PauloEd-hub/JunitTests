package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void itShouldCheckIfStudentEmailExists() {

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


    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {

        //given
        String email = "jamila@gmail.com";


        //when
        Boolean existsEmail = underTest.selectExistsEmail(email);

        //then
        assertThat(existsEmail).isFalse();

    }
}
