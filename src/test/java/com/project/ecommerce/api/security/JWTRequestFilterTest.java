package com.project.ecommerce.api.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.UsersRepository;
import com.project.ecommerce.service.JWTService;

@SpringBootTest
@AutoConfigureMockMvc
public class JWTRequestFilterTest {

    private JWTService jwtService;

    private MockMvc mockMvc;

    private UsersRepository usersRepository;

    private static final String AUTHENTICATED_PATH = "/order";


    public JWTRequestFilterTest(@Autowired JWTService jwtService,@Autowired MockMvc mockMvc,@Autowired UsersRepository usersRepository){
        this.jwtService=jwtService;
        this.usersRepository=usersRepository;
        this.mockMvc=mockMvc;
    }

    @Test
    public void testUnauthenticatedRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHENTICATED_PATH)).andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testBadToken() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHENTICATED_PATH).header("Authorization", "BadTokenThatIsNotValid"))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHENTICATED_PATH).header("Authorization", "Bearer BadTokenThatIsNotValid"))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
    }

    
    @Test
    public void testSuccessful() throws Exception {
        Users user = usersRepository.findByUsername("UserA").get();
        String token = jwtService.generateJWT(user);
        mockMvc.perform(MockMvcRequestBuilders.get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }
    }
