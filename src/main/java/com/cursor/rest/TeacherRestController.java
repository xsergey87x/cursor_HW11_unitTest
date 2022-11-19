package com.cursor.rest;

import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import com.cursor.service.serviceInterfaces.TeacherTableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherRestController {

    private final TeacherTableService teacherTableService;

    public TeacherRestController(TeacherTableService teacherTableService) {
        this.teacherTableService = teacherTableService;
    }

    @GetMapping(value = "/getAll")
    public List<Teacher> getAllTeacher() {
        return teacherTableService.getAllTeacher();
    }

    @GetMapping(value = "/addGroupForTeacherById/{teacherId}/{groupId}")
    public Teacher addGroupForTeacherById(@PathVariable Long teacherId, @PathVariable Long groupId) {
        return teacherTableService.addGroupForTeacherById(groupId, teacherId);
    }

    @PostMapping(value = "/addGroupForTeacher")
    public Teacher addGroupForTeacher(@RequestBody StudentsGroup studentsGroup, @RequestParam Long groupId) {
        return teacherTableService.addGroupForTeacher(studentsGroup, groupId);
    }

    @DeleteMapping("/deleteGroupFromTeacher")
    public void deleteGroupFromTeacher(@RequestParam Long groupId) {
        teacherTableService.deleteGroupFromTeacher(groupId);
    }

    @GetMapping("/teacherNumberOfGroups")
    public int teacherNumberOfGroups(@RequestParam Long teacherId) {
        return teacherTableService.getAmountGroupInTeacher(teacherId);
    }

    @PostMapping(value = "/addTeacher")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherTableService.create(teacher);
    }


}
