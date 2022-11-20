package com.cursor;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.serviceInterfaces.GroupTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GroupTableServiceImplTest {

    @Mock
    StudentsGroupRepository studentsGroupRepositoryMock;

    @Mock
    StudentRepository studentRepositoryMock;

    @InjectMocks
    GroupTableServiceImpl groupTableServiceImpl;

    @Test
    public void testAddStudentToGroup()
    {
        Student student = new Student(1L,"John", "Smith","male",33,null);
        StudentsGroup group = new StudentsGroup(2L,"firstGroup", List.of(student));
       student.setStudentsGroup(group);
        groupTableServiceImpl.create(group);
        studentRepositoryMock.save(student);

        Mockito.when(studentsGroupRepositoryMock.save(group))
                .thenReturn(group);

        StudentsGroup newGroup = groupTableServiceImpl.addStudentToGroup(student,2L);

       // assertThat(newGroup).isSameAs(group);
    }
}
