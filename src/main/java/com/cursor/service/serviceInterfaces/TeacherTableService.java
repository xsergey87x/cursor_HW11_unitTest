package com.cursor.service.serviceInterfaces;

import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import java.util.List;

public interface TeacherTableService {

    Teacher addGroupForTeacher(StudentsGroup group, Long teacherId);
    Teacher addGroupForTeacherById(Long studentsGroupId, Long teacherId);

    void deleteGroupFromTeacher(Long groupId);

    int getAmountGroupInTeacher(Long teacherId);

    Teacher create(Teacher teacher);
    List<Teacher> getAllTeacher();
}
