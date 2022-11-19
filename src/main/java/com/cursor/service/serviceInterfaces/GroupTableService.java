package com.cursor.service.serviceInterfaces;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;

import java.util.List;

public interface GroupTableService {

    void addStudentToGroup(Student student, Long id);

    void deleteStudentById(Long id);
    void deleteStudent(Student student);


    StudentsGroup create(StudentsGroup studentsGroup);

    List<StudentsGroup> getAllGroups();

    void addStudentToGroupById(Long studentId, Long groupId);
}
