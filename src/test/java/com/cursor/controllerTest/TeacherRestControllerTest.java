package com.cursor.controllerTest;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import com.cursor.service.serviceInterfaces.StudentTableService;
import com.cursor.service.serviceInterfaces.TeacherTableService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

//    @Test
//    public void testAddGroupForTeacherById() throws Exception
//    {
//        when(teacherTableServiceMock.addGroupForTeacherById(2L,1L)).thenReturn(new Teacher("Will","Peterson","male",44));
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/teacher/addGroupForTeacherById/{teacherId}/{groupId}",1L,2L))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Will"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Peterson"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(44));
//    }

    @Test
    public void testAddGroupForTeacher() throws Exception
    {
        Gson gson = new Gson();
        String requestBody = gson.toJson(new StudentsGroup("firstGroup"));

        this.mockMvc.perform(
                        post("/teacher/addGroupForTeacher").param("groupId", "1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTeacher() throws Exception
    {
        Gson gson = new Gson();
        String requestBody = gson.toJson(new Teacher("Will","Peterson","male",44));

        this.mockMvc.perform(post("/teacher/addTeacher")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void testTeacherNumberOfGroups() throws Exception
    {
        when(teacherTableServiceMock.getAmountGroupInTeacher(1L)).thenReturn(2);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/teacher/teacherNumberOfGroups").param("teacherId","1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("2"));
    }

    @Test
    public void testDeleteGroupFromTeacher() throws Exception
    {
        this.mockMvc.perform(delete("/teacher/deleteGroupFromTeacher").param("groupId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
