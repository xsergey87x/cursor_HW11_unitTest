package com.cursor.rest;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.service.serviceInterfaces.GroupTableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/studentsGroup")
public class StudentGroupRestController {

    private final GroupTableService groupTableService;

    public StudentGroupRestController(GroupTableService groupTableService) {
        this.groupTableService = groupTableService;
    }

    @GetMapping(value = "/getAll")
    public List<StudentsGroup> getAllStudentsGroup() {
        return groupTableService.getAllGroups();
    }

    @PostMapping(value = "/addGroup")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentsGroup createGroup(@RequestBody StudentsGroup studentsGroup) {
        return groupTableService.create(studentsGroup);
    }

    @PostMapping("/addStudentToGroup")
    public void addStudentToGroup(@RequestBody Student student, @RequestParam Long groupId) {
        groupTableService.addStudentToGroup(student, groupId);
    }


    @DeleteMapping("/deleteStudentFromGroup")
    public void deleteStudentFromGroup(@RequestBody Student student) {
        groupTableService.deleteStudent(student);
    }
}
