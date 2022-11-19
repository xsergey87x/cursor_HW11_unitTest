package com.cursor.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @OneToMany(mappedBy = "studentsGroup", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference
    private Teacher teacher;

    public void addStudent(Student student) {
        students.add(student);
        student.setStudentsGroup(this);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }
}
