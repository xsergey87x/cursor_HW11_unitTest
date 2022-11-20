package com.cursor;


import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.service.serviceInterfaces.StudentTableService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void getAllStudentsByTeacher() throws Exception
    {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("John", "Smith","male",33));
        when(studentTableServiceMock.getAllStudentByTeacher(1L)).thenReturn(studentList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/student/getAllByTeacher/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Smith"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(33));
    }

    @Test
    public void testAddStudent() throws Exception
    {
        Gson gson = new Gson();
        String requestBody = gson.toJson(new Student("Johnson", "Geits","male",25));

        this.mockMvc.perform(
                        post("/student/addStudent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isCreated());
    }

}
