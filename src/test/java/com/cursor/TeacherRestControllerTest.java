package com.cursor;

import com.cursor.service.serviceInterfaces.StudentTableService;
import com.cursor.service.serviceInterfaces.TeacherTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TeacherRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TeacherTableService teacherTableServiceMock;

    @Test
    public void testGetAll() throws Exception
    {

    }
}
