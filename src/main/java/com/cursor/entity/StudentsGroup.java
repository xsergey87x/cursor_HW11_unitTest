package com.cursor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



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

    public StudentsGroup(Long id, String name, List<Student> students, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.teacher = teacher;
    }


    @OneToMany(mappedBy = "studentsGroup", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    public void addStudent(Student student) {
        if (this.getStudents() != null)
        {
            List<Student> oldStudent = new ArrayList<>();
            oldStudent.addAll(this.getStudents());
            oldStudent.add(student);
            this.setStudents(oldStudent);
        } else
        {
            this.setStudents(List.of(student));
        }
        student.setStudentsGroup(this);
    }

    public void deleteStudent(Student student) {
        List<Student> studentsList = new ArrayList<>();
        if ((student != null)&&(student.getStudentsGroup() != null)) {
            studentsList.addAll(this.getStudents());
            studentsList.remove(student);
            student.setStudentsGroup(null);
        }
        this.setStudents(studentsList);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "StudentsGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
