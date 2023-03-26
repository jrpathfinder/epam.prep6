package com.epam.learn.prep6.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventsControllerUnitTest {

    private MockMvc mockMvc;
    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EventsController()).build();
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenGreetURI_whenMockMVC_thenVerifyResponse() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk()).andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.message").value("Hello World!!!"));
    }
}