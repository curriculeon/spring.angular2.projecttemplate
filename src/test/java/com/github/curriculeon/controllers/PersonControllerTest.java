package com.github.curriculeon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.curriculeon.models.Person;
import com.github.curriculeon.repositories.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


/**
 * @author leon on 8/30/18.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private PersonRepository repository;

    @Test
    public void testShow() throws Exception {
        Long givenId = 1L;
        Person person = new Person();
        BDDMockito
                .given(repository.findById(givenId))
                .willReturn(Optional.of(person));
        String expectedContent = new ObjectMapper().writeValueAsString(person);
        this.mvc.perform(MockMvcRequestBuilders
                .get("/bakers/" + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testCreate() throws Exception {
        Person person = new Person();
        BDDMockito
                .given(repository.save(person))
                .willReturn(person);

        String expectedContent = new ObjectMapper().writeValueAsString(person);
        this.mvc.perform(MockMvcRequestBuilders
                .post("/bakers/")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }
}
