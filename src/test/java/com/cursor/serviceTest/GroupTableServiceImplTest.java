package com.cursor.serviceTest;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.serviceInterfaces.StudentTableService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GroupTableServiceImplTest {

    @Mock
    private StudentsGroupRepository studentsGroupRepositoryMock = Mockito.mock(StudentsGroupRepository.class);;

    @Mock
    private StudentRepository studentRepositoryMock = Mockito.mock(StudentRepository.class);;

    @InjectMocks
    private GroupTableServiceImpl groupTableServiceMock = new GroupTableServiceImpl(studentsGroupRepositoryMock,studentRepositoryMock);


    @Test
    public void testAddStudentToGroup()
    {
        Student student = new Student(1L,"John", "Smith","male",33,null);
        StudentsGroup group = new StudentsGroup(2L,"firstGroup", null);

        given(studentsGroupRepositoryMock.findById(2L)).willReturn(Optional.of(group));
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);

        StudentsGroup newGroup = groupTableServiceMock.addStudentToGroup(student,2L);
       assertThat(newGroup).isSameAs(group);
    }

    @Test
    public void testDeleteStudent()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup",null);
        Student student = new Student(1L,"John", "Smith","male",33,null);
        group.addStudent(student);

        given(studentsGroupRepositoryMock.findById(2L)).willReturn(Optional.of(group));
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);

        groupTableServiceMock.deleteStudent(student);

        Assertions.assertFalse(group.getStudents().contains(student));
    }

    @Test
    public void testCreateGroup()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup",null);
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);

        assertEquals(group, groupTableServiceMock.create(group));
    }

    @Test
    public void getAllGroups()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup",null);
        given(studentsGroupRepositoryMock.findAll()).willReturn(List.of(group));

        Assertions.assertEquals(List.of(group),groupTableServiceMock.getAllGroups());
    }
}
