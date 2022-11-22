package com.cursor.serviceTest;

import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.TeacherTableServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TeacherTableServiceImplTest {

    @Mock
    private TeacherRepository teacherRepositoryMock = Mockito.mock(TeacherRepository.class);;

    @Mock
    private StudentsGroupRepository studentsGroupRepositoryMock = Mockito.mock(StudentsGroupRepository.class);;

    @InjectMocks
    private TeacherTableServiceImpl teacherTableServiceMock = new TeacherTableServiceImpl(teacherRepositoryMock,studentsGroupRepositoryMock);

    @Test
    public void testAddGroupForTeacher()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup", null,null);
        Teacher teacher = new Teacher(3L,"Peter","Johnson","male",55, null);

        given(teacherRepositoryMock.findById(3L)).willReturn(Optional.of(teacher));
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);

        teacherTableServiceMock.addGroupForTeacher(group,3L);
        Assertions.assertTrue(teacher.getStudentsGroups().contains(group));
    }

    @Test
    public void testDeleteGroupFromTeacher()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup", null,null);
        Teacher teacher = new Teacher(3L,"Peter","Johnson","male",55, null);
        teacher.setStudentsGroups(List.of(group));
        group.setTeacher(teacher);

        given(studentsGroupRepositoryMock.findById(2L)).willReturn(Optional.of(group));
        given(teacherRepositoryMock.save(teacher)).willReturn(teacher);

        teacherTableServiceMock.deleteGroupFromTeacher(2L);
        Assertions.assertFalse(teacher.getStudentsGroups().contains(group));
    }

    @Test
    public void getAmountGroupInTeacher()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup", null,null);
        Teacher teacher = new Teacher(3L,"Peter","Johnson","male",55, null);
        teacher.setStudentsGroups(List.of(group));
        group.setTeacher(teacher);

        given(teacherRepositoryMock.findById(3L)).willReturn(Optional.of(teacher));


    }

}
