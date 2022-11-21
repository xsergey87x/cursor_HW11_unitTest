package com.cursor.service;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.service.serviceInterfaces.GroupTableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupTableServiceImpl implements GroupTableService {

    private final StudentsGroupRepository studentsGroupRepository;
    private final StudentRepository studentRepository;

    public GroupTableServiceImpl(StudentsGroupRepository studentsGroupRepository, StudentRepository studentRepository) {
        this.studentsGroupRepository = studentsGroupRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentsGroup addStudentToGroupById(Long studentId, Long groupId) {
        return addStudentToGroup(studentRepository.findById(studentId).get(), groupId);
    }

    @Override
    public StudentsGroup addStudentToGroup(Student student, Long id) {
        Optional<StudentsGroup> studentsGroup = studentsGroupRepository.findById(id);
        if ((student != null)&&(studentsGroup.get() != null))
        {
            Student newStudent = student;
            newStudent.setStudentsGroup(studentsGroup.get());
            studentRepository.save(student);
            studentsGroup.get().addStudent(student);
        }
        return studentsGroupRepository.save(studentsGroup.get());
    }

    @Override
    public StudentsGroup deleteStudent(Student student) {
        StudentsGroup studentsGroup = student.getStudentsGroup();
        if ((studentsGroup != null)&&(student != null))
        {
            studentsGroup.deleteStudent(student);
        }
        return studentsGroupRepository.save(studentsGroup);
    }

    @Override
    public StudentsGroup deleteStudentById(Long studentId) {
        Optional<StudentsGroup> studentsGroup = studentsGroupRepository.findById(studentId);
        studentsGroup.get().deleteStudent(studentRepository.findById(studentId).get());
        return studentsGroupRepository.save(studentsGroup.get());
    }

    @Override
    public StudentsGroup create(StudentsGroup studentsGroup) {
        return studentsGroupRepository.save(studentsGroup);
    }

    @Override
    public List<StudentsGroup> getAllGroups() {
        return studentsGroupRepository.findAll();
    }


}
