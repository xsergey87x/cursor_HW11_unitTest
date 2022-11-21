package com.cursor.serviceTest;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.StudentTableServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class StudentTableServiceImplTest {

    @Mock
    private StudentRepository studentRepositoryMock = Mockito.mock(StudentRepository.class);

    @Mock
    private TeacherRepository teacherRepositoryMock = Mockito.mock(TeacherRepository.class);

    @InjectMocks
    private StudentTableServiceImpl studentTableServiceMock = new StudentTableServiceImpl(studentRepositoryMock, teacherRepositoryMock);

    @Test
    public void  testGetAllStudentByTeacher()
    {
        StudentsGroup group = new StudentsGroup(2L,"firstGroup",null);
        Student student = new Student(1L,"John", "Smith","male",33,null);
        group.addStudent(student);
        Teacher teacher = new Teacher(3L,"Peter","Johnson","male",55, List.of(group));

        given(teacherRepositoryMock.findById(3L)).willReturn(java.util.Optional.of(teacher));

        Assertions.assertEquals(List.of(student),studentTableServiceMock.getAllStudentByTeacher(3L));
    }

    @Test
    public void testCreateStudent()
    {
        Student student = new Student(1L,"John", "Smith","male",33,null);
        given(studentRepositoryMock.save(student)).willReturn(student);

        assertEquals(student, studentTableServiceMock.create(student));
    }

    @Test
    public void getAllStudent()
    {
        Student student = new Student(1L,"John", "Smith","male",33,null);
        given(studentRepositoryMock.findAll()).willReturn(List.of(student));

        Assertions.assertEquals(List.of(student),studentTableServiceMock.getAllStudent());
    }
}
