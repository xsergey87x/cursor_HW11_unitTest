package com.cursor.serviceTest;

import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.TeacherTableServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TeacherTableServiceImplTest {

    // @Mock
    private TeacherRepository teacherRepositoryMock = Mockito.mock(TeacherRepository.class);
    //  @Mock
    private StudentsGroupRepository studentsGroupRepositoryMock = Mockito.mock(StudentsGroupRepository.class);
    //  @InjectMocks
    private TeacherTableServiceImpl teacherTableServiceMock = new TeacherTableServiceImpl(teacherRepositoryMock, studentsGroupRepositoryMock);

    private StudentsGroup group;
    private Teacher teacher;

    @BeforeEach
    void initSetup() {
        group = new StudentsGroup(2L, "firstGroup", null, null);
        teacher = new Teacher(3L, "Peter", "Smith", "male", 55, null);
    }

    @Test
    public void testAddGroupForTeacher() {

        given(teacherRepositoryMock.findById(3L)).willReturn(Optional.of(teacher));
        given(studentsGroupRepositoryMock.save(group)).willReturn(group);
        teacherTableServiceMock.addGroupForTeacher(group, 3L);
        Assertions.assertTrue(teacher.getStudentsGroups().contains(group));
    }

    @Test
    public void testDeleteGroupFromTeacher() {

        teacher.setStudentsGroups(List.of(group));
        group.setTeacher(teacher);
        given(studentsGroupRepositoryMock.findById(2L)).willReturn(Optional.of(group));
        given(teacherRepositoryMock.save(teacher)).willReturn(teacher);
        teacherTableServiceMock.deleteGroupFromTeacher(2L);
        Assertions.assertFalse(teacher.getStudentsGroups().contains(group));
    }

    @Test
    public void getAmountGroupInTeacher() {

        teacher.setStudentsGroups(List.of(group));
        group.setTeacher(teacher);
        given(teacherRepositoryMock.findById(3L)).willReturn(Optional.of(teacher));
        Assertions.assertEquals(1, teacherTableServiceMock.getAmountGroupInTeacher(3L));
    }

    @Test
    public void testCreateTeacher() {
        given(teacherRepositoryMock.save(teacher)).willReturn(teacher);
        assertEquals(teacher, teacherTableServiceMock.create(teacher));
    }

    @Test
    public void testGetAllTeacher() {
        given(teacherRepositoryMock.findAll()).willReturn(List.of(teacher));
        Assertions.assertEquals(List.of(teacher), teacherTableServiceMock.getAllTeacher());
    }
}
