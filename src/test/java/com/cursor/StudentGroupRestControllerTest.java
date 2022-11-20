package com.cursor;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.repository.StudentRepository;
import com.cursor.repository.StudentsGroupRepository;
import com.cursor.rest.StudentGroupRestController;
import com.cursor.service.GroupTableServiceImpl;
import com.cursor.service.StudentTableServiceImpl;
import com.cursor.service.serviceInterfaces.GroupTableService;
import com.cursor.service.serviceInterfaces.StudentTableService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
//@WebMvcTest(value = StudentGroupRestController.class)
@AutoConfigureMockMvc
public class StudentGroupRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupTableService groupTableServiceMock;

    @Test
    public void getAllStudentsGroup() throws Exception {

        List<StudentsGroup> studentsGroupsList = new ArrayList<>();
        studentsGroupsList.add(new StudentsGroup("firstStudentGroup"));
        studentsGroupsList.add(new StudentsGroup("secondStudentGroup"));

        when(groupTableServiceMock.getAllGroups()).thenReturn(studentsGroupsList);

      this.mockMvc.perform(MockMvcRequestBuilders.get("/studentsGroup/getAll"))
                .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("firstStudentGroup"))
              .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("secondStudentGroup"));
    }

    @Test
    public void addGroup() throws Exception {

        Gson gson = new Gson();
        String requestBody = gson.toJson(new StudentsGroup("firstGroup"));

        this.mockMvc.perform(
                post("/studentsGroup/addGroup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void addStudentsToGroup() throws Exception{

        Gson gson = new Gson();
        String requestBody = gson.toJson(new Student("John", "Smith","male",33));

        this.mockMvc.perform(
                        post("/studentsGroup/addStudentToGroup").param("groupId","1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentFromGroupById() throws Exception
    {
      this.mockMvc.perform(delete("/studentsGroup/deleteStudentFromGroupById").param("studentId", "1")
                      .contentType(MediaType.APPLICATION_JSON)
                      .accept(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentFromGroup() throws Exception
    {
        Gson gson = new Gson();
        String requestBody = gson.toJson(new Student("John", "Smith","male",33));

        this.mockMvc.perform(delete("/studentsGroup/deleteStudentFromGroup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }
}
