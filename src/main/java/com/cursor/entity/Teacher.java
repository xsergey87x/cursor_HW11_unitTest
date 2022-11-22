package com.cursor.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@RequiredArgsConstructor
@Entity
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String firstName;
    String lastName;
    String gender;
    int age;

    @OneToMany(mappedBy = "teacher")
    @JsonManagedReference
    private List<StudentsGroup> studentsGroups = new ArrayList<>();

    public Teacher(String firstName, String lastName, String gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public Teacher(Long id, String firstName, String lastName, String gender, int age, List<StudentsGroup> studentsGroups) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.studentsGroups = studentsGroups;
    }

    public void addStudentsGroup(StudentsGroup studentsGroup) {
        if (this.getStudentsGroups() != null)
        {
            List<StudentsGroup> oldStudentGroup = new ArrayList<>();
            oldStudentGroup.addAll(this.getStudentsGroups());
            oldStudentGroup.add(studentsGroup);
            this.setStudentsGroups(oldStudentGroup);
        } else
        {
            this.setStudentsGroups(List.of(studentsGroup));
        }
        studentsGroup.setTeacher(this);
    }

    public void deleteStudentsGroup(StudentsGroup studentsGroup) {
        List<StudentsGroup> groupList = new ArrayList<>();
        if (this.getStudentsGroups().contains(studentsGroup))
        {
            groupList.addAll(this.getStudentsGroups());
            groupList.remove(studentsGroup);
            studentsGroup.setTeacher(null);
        }
        this.setStudentsGroups(groupList);
    }

}
