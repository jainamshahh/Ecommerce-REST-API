package com.project.ecommerce.api.contoller.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

    private MockMvc mockMvc;

    public ProductTest(@Autowired MockMvc mockMvc){
        this.mockMvc=mockMvc;
    }

    @Test
    public void testProductTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/product")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }
}
