package com.udilov.it.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udilov.it.models.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParsingTest {

    private final ClassLoader cl = JsonParsingTest.class.getClassLoader();

    @Test
    void jsonParsingWithJacksonLibraryTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("student.json");

            Reader reader = new InputStreamReader(is)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Student student = objectMapper.readValue(reader, Student.class);

            Assertions.assertEquals("35", student.getAge());
            Assertions.assertEquals("Alex", student.getFirstName());
            Assertions.assertEquals("Udilov", student.getLastName());
            Assertions.assertEquals("male", student.getGender());
            Assertions.assertArrayEquals(new String[] {"fishing", "testing", "reading"}, student.getHobbies().toArray());
            Assertions.assertArrayEquals(new String[] {"dog", "cat"}, student.getAnotherStudentInformation()
                    .getPets().toArray());
        }
    }
}
