package com.example.finaltesttask.web;

import com.example.finaltesttask.FinalTestTaskApplication;
import com.example.finaltesttask.entity.User;
import com.example.finaltesttask.service.serviceImpl.UserServiceImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {
        FinalTestTaskApplication.class
})
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    UserServiceImplementation userServiceImplementation;
    public static String asJsonString(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(object);
        return json;
    }
    @Test
    public void testSaveUser() throws Exception {
        User user = User.builder()
                .id(8L)
                .firstName("Valera")
                .lastName("Bond")
                .age(20)
                .build();
        RequestBuilder request = post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user));
        mvc.perform(request)
                .andExpect(status().isCreated())
                .andDo(print());

    }

    @Test
    public void testGetUser() throws Exception {
        User user = User.builder()
                .id(8L)
                .firstName("Valera")
                .lastName("Bond")
                .age(20)
                .build();
        when(userServiceImplementation.getById(user.getId())).thenReturn(user);
        mvc.perform(get("/user/{id}", user.getId())).andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andDo(print());
    }
}