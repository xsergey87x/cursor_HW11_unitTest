package com.cursor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
public class Student {

    public Student() {
    }

    public Student(String firstName, String lastName, String gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }
    public Student(Long id, String firstName, String lastName, String gender, int age, StudentsGroup studentsGroup) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.studentsGroup = studentsGroup;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String firstName;
    String lastName;
    String gender;
    int age;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "studentsGroup_id")
    @JsonBackReference
    private StudentsGroup studentsGroup;

}
