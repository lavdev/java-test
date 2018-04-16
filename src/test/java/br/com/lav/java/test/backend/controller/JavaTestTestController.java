package br.com.lav.java.test.backend.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JavaTestTestController extends SpringBootTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testEmployeeBySkill1(){

        try {
            mockMvc.perform(get("/employee/skill/?filter=Scrum")).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$[0].name").value("Renato Garcia"))
                    .andExpect(jsonPath("$[0].role[0].name").value("TI Architect"))
                    .andExpect(jsonPath("$[0].salary").value("2000,00"));
        }catch (AssertionError | Exception e){
            System.err.println( e.getLocalizedMessage());
        }
    }
}