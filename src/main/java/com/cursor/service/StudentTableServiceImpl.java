package com.cursor.service;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.serviceInterfaces.StudentTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentTableServiceImpl implements StudentTableService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentTableServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> getAllStudentByTeacher(Long teacherId) {
        List<Student> teacherStudents = new ArrayList<>();
        teacherRepository.findById(teacherId).get().getStudentsGroups().forEach(studentsGroup -> teacherStudents.addAll(studentsGroup.getStudents()));
        return teacherStudents;
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
