package com.cursor;


import com.cursor.entity.Student;
import com.cursor.service.serviceInterfaces.StudentTableService;
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
public class StudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentTableService studentTableServiceMock;

    @Test
    public void testGetAllStudent() throws Exception
    {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("John", "Smith","male",33));
        studentList.add(new Student("Johnson", "Geits","male",25));

        when(studentTableServiceMock.getAllStudent()).thenReturn(studentList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/student/getAll"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(33))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName").value("Johnson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].lastName").value("Geits"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].age").value(25));
    }

}