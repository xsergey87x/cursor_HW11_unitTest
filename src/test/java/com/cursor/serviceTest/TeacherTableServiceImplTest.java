package com.cursor.serviceTest;

import com.cursor.entity.Teacher;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.repository.TeacherRepository;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.TeacherTableServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeacherTableServiceImplTest {

    @Mock
    private TeacherRepository teacherRepositoryMock = Mockito.mock(TeacherRepository.class);;

    @Mock
    private StudentsGroupRepository studentsGroupRepositoryMock = Mockito.mock(StudentsGroupRepository.class);;

    @InjectMocks
    private TeacherTableServiceImpl teacherTableServiceMock = new TeacherTableServiceImpl(teacherRepositoryMock,studentsGroupRepositoryMock);


}
