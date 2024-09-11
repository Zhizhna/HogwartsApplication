package com.sky.hogwarts.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.hogwarts.Model.Student;
import com.sky.hogwarts.Service.AvatarService;
import com.sky.hogwarts.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @MockBean
    private AvatarService avatarService;

    @Test
    void shouldGetStudent() throws Exception {

        Long studentId = 1L;
        Student student = new Student("Maria", 16);

        when(studentService.read(studentId)).thenReturn(student);

        ResultActions perform = mockMvc.perform(get("/students/{id}", studentId));

        perform
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }

    @Test
    void shouldCreateStudent() throws Exception {
        // given
        Long studentId = 1L;
        Student student = new Student("Maria", 16);
        Student savedStudent = new Student("Maria", 16);
        savedStudent.setId(studentId);

        when(studentService.create(student)).thenReturn(savedStudent);

        ResultActions perform = mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        perform
                .andExpect(jsonPath("$.id").value(savedStudent.getId()))
                .andExpect(jsonPath("$.name").value(savedStudent.getName()))
                .andExpect(jsonPath("$.age").value(savedStudent.getAge()))
                .andDo(print());
    }

    @Test
    void shouldUpdateStudent() throws Exception {

        Long studentId = 1L;
        Student student = new Student("Maria", 17);

        when(studentService.update(studentId, student)).thenReturn(student);

        ResultActions perform = mockMvc.perform(put("/students/{id}", studentId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)));

        perform
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }
}
