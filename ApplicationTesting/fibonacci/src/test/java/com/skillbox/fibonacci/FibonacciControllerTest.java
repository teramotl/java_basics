package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test index")
    void testGetFibonacciNumberValidIndex() throws Exception {
        int index = 10;
        int expectedFibonacciNumber = 55; // The 10th Fibonacci number

        mockMvc.perform(get("/fibonacci/{index}", index))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.index").value(index))
                .andExpect(jsonPath("$.value").value(expectedFibonacciNumber));
    }

    @Test
    @DisplayName("Test invalid index")
    void testGetFibonacciNumberInvalidIndex() throws Exception {
        int invalidIndex = 0;

        mockMvc.perform(get("/fibonacci/{index}", invalidIndex))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Index should be greater or equal to 1"));
    }

    @Test
    @DisplayName("Test negative index")
    void testGetFibonacciNumberNegativeIndex() throws Exception {
        int negativeIndex = -1;

        mockMvc.perform(get("/fibonacci/{index}", negativeIndex))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Index should be greater or equal to 1"));
    }
}