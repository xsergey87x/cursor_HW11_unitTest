package com.cursor;

import com.cursor.entity.Student;
import com.cursor.entity.Teacher;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher("Will","Peterson","male",44));
        when(teacherTableServiceMock.getAllTeacher()).thenReturn(teacherList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/teacher/getAll"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Will"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Peterson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(44));
    }

    @Test
    public void testAddGroupForTeacherById() throws Exception
    {
        when(teacherTableServiceMock.addGroupForTeacherById(2L,1L)).thenReturn(new Teacher("Will","Peterson","male",44));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/teacher/addGroupForTeacherById/{teacherId}/{groupId}",1L,2L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Will"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Peterson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(44));
    }
}
