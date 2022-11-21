package com.cursor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@ToString
@Entity
public class StudentsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    public StudentsGroup() {
    }

    public StudentsGroup(String name) {
        this.name = name;
    }

    public StudentsGroup(Long id, String name, List<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    @OneToMany(mappedBy = "studentsGroup", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    public void addStudent(Student student) {
        List<Student> oldStudent = new ArrayList<>();
        oldStudent.addAll(this.getStudents());
        oldStudent.add(student);
        student.setStudentsGroup(this);
        this.setStudents(oldStudent);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }
}
