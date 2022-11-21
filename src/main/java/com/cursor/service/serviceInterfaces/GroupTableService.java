package com.cursor.service.serviceInterfaces;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;

import java.util.List;

public interface GroupTableService {

    StudentsGroup addStudentToGroup(Student student, Long id);

    StudentsGroup deleteStudent(Student student);


    StudentsGroup create(StudentsGroup studentsGroup);

    List<StudentsGroup> getAllGroups();

    StudentsGroup addStudentToGroupById(Long studentId, Long groupId);
}
