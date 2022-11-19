package com.cursor.service.serviceInterfaces;

import com.cursor.entity.Student;

import java.util.List;

public interface StudentTableService {

    List<Student> getAllStudentByTeacher(Long teacherId);

     Student create(Student student);

    List<Student> getAllStudent();
}
