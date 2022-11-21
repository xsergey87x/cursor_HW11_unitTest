package com.cursor;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.serviceInterfaces.StudentTableService;
import org.junit.Test;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GroupTableServiceImplTest {

    @Mock
    private StudentsGroupRepository studentsGroupRepositoryMock = Mockito.mock(StudentsGroupRepository.class);;

    @Mock
    private StudentRepository studentRepositoryMock = Mockito.mock(StudentRepository.class);;

    @InjectMocks
    private GroupTableServiceImpl groupTableServiceMock = new GroupTableServiceImpl(studentsGroupRepositoryMock,studentRepositoryMock);

//    @BeforeEach
//    public void setUp()
//    {
//        Student student = new Student(1L,"John", "Smith","male",33,null);
//        StudentsGroup group = new StudentsGroup(2L,"firstGroup", List.of(student));
//        student.setStudentsGroup(group);
//    }

    @Test
    public void testAddStudentToGroup()
    {
        Student student = new Student(1L,"John", "Smith","male",33,null);
        StudentsGroup group = new StudentsGroup(2L,"firstGroup", List.of(student));
        student.setStudentsGroup(group);

        given(studentsGroupRepositoryMock.findById(2L)).willReturn(Optional.of(group));
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);

        StudentsGroup newGroup = groupTableServiceMock.addStudentToGroup(student,2L);
       assertThat(newGroup).isSameAs(group);
    }

    @Test
    public void testDeleteStudent()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup",null);
        Student student = new Student(1L,"John", "Smith","male",33,group);
        group.setStudents(List.of(student));

        given(studentsGroupRepositoryMock.findById(2L)).willReturn(Optional.of(group));
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);

        System.out.println(student + "**" + student.getStudentsGroup().getId());
        StudentsGroup newGroup = groupTableServiceMock.deleteStudent(student);
        assertThat(newGroup).isSameAs(group);
    }
}
