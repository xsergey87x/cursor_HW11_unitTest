package com.cursor.serviceTest;

import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.StudentTableServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }
}
