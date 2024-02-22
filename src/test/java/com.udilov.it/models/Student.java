package com.udilov.it.models;

import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private List<String> hobbies;
    private AnotherStudentInformation anotherStudentInformation;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public AnotherStudentInformation getAnotherStudentInformation() {
        return anotherStudentInformation;
    }
}
