package com.project.ecommerce.api.contoller.order;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ecommerce.model.Orders;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    private MockMvc mockMvc;

    public OrderControllerTest(@Autowired MockMvc mockMvc){
        this.mockMvc=mockMvc;
    }

    @Test
    @WithUserDetails("UserA")
    public void testUserAAuthenticatedOrderList() throws Exception {
        testAuthenticatedListBelongsToUser("UserA");
    }


    private void testAuthenticatedListBelongsToUser(String username) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
            .andExpect(result -> {
            String json = result.getResponse().getContentAsString();
            List<Orders> orders = new ObjectMapper().readValue(json, new TypeReference<List<Orders>>() {});
            for (Orders order : orders) {
                Assertions.assertEquals(username, order.getUser().getUsername(), "Order list should only be orders belonging to the user.");
            }
            });
    }

    @Test
    public void testUnauthenticatedOrderList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/order")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
    }
}
