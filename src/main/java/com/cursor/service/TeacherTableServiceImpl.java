package com.cursor.service;

import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.serviceInterfaces.TeacherTableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherTableServiceImpl implements TeacherTableService {

    private final TeacherRepository teacherRepository;
    private final StudentsGroupRepository studentsGroupRepository;

    public TeacherTableServiceImpl(TeacherRepository teacherRepository, StudentsGroupRepository studentsGroupRepository) {
        this.teacherRepository = teacherRepository;
        this.studentsGroupRepository = studentsGroupRepository;
    }

    @Override
    public Teacher addGroupForTeacher(StudentsGroup group, Long teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        studentsGroupRepository.save(group);
        teacher.get().addStudentsGroup(group);
        teacherRepository.save(teacher.get());
        return teacher.get();
    }

    @Override
    public Teacher addGroupForTeacherById(Long studentsGroupId, Long teacherId) {
        Optional<StudentsGroup> group = studentsGroupRepository.findById(studentsGroupId);
        return addGroupForTeacher(group.get(), teacherId);
    }

    @Override
    public void deleteGroupFromTeacher(Long groupId) {
        StudentsGroup group = studentsGroupRepository.findById(groupId).get();
        Teacher teacher = group.getTeacher();
        teacher.deleteStudentsGroup(group);
        teacherRepository.save(teacher);
    }

    @Override
    public int getAmountGroupInTeacher(Long teacherId) {
        return teacherRepository.findById(teacherId).get().getStudentsGroups().size();
    }

    @Override
    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }
}
