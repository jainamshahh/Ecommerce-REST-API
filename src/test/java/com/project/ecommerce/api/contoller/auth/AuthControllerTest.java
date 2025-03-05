package com.project.ecommerce.api.contoller.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerce.api.model.UserRegisterationBody;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    private MockMvc mockMvc;

    public AuthControllerTest(@Autowired MockMvc mockMvc){
        this.mockMvc=mockMvc;
    }

    @Test
    @Transactional
    public void testRegisterUser() throws JsonProcessingException, Exception{

        ObjectMapper mapper = new ObjectMapper();
        UserRegisterationBody body = new UserRegisterationBody();
        body.setEmail("AuthenticationControllerTest$testRegister@junit.com");
        body.setFirstName("FirstName");
        body.setLastName("LastName");
        body.setPassword("Password123");
        // Null or blank username.
        body.setUsername(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setUsername("");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setUsername("AuthenticationControllerTest$testRegister");
        // Null or blank email.
        body.setEmail(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setEmail("");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setEmail("AuthenticationControllerTest$testRegister@junit.com");
        // Null or blank password.
        body.setPassword(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setPassword("");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setPassword("Password123");
        // Null or blank first name.
        body.setFirstName(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setFirstName("");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setFirstName("FirstName");
        // Null or blank last name.
        body.setLastName(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setLastName("");
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
        body.setLastName("LastName");
        // Valid registration.
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body)))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));

    }


}
