package com.cursor.rest;

import com.cursor.entity.Student;
import com.cursor.service.serviceInterfaces.StudentTableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentRestController {

    private final StudentTableService studentTableService;

    public StudentRestController(StudentTableService studentTableService) {
        this.studentTableService = studentTableService;
    }

    @GetMapping(value = "/getAll")
    public List<Student> getAllStudents()
    {
        return studentTableService.getAllStudent();
    }

    @GetMapping(value = "/getAllByTeacher/{teacherId}")
    public List<Student> getAllStudentByTeacher(@PathVariable Long teacherId)
    {
        return studentTableService.getAllStudentByTeacher(teacherId);
    }

    @PostMapping(value = "/addStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentTableService.create(student);
    }

    @GetMapping(value = "/addTestStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createTestStudent() {
        return studentTableService.create(new Student("John","Smith","male",33));
    }

}
