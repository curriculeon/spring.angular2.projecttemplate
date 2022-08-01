package com.github.curriculeon.services;


import com.github.curriculeon.MyApplication;

import com.github.curriculeon.controllers.PersonController;
import com.github.curriculeon.models.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyApplication.class)
public class PersonServiceTest {
    @MockBean
    private PersonService service;

    private PersonController controller;

    @Before
    public void setup(){
        this.controller = new PersonController(service);
    }


    @Test
    public void testCreate() {
        // Given
        HttpStatus expected = HttpStatus.CREATED;
        Person expectedPerson = new Person();
        BDDMockito
                .given(service.create(expectedPerson))
                .willReturn(expectedPerson);

        // When
        ResponseEntity<Person> response = controller.create(expectedPerson);
        HttpStatus actual = response.getStatusCode();
        Person actualPerson = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPerson, actualPerson);
    }


    @Test
    public void testShow() {
        // Given
        Long expectedId = 1L;
        HttpStatus expected = HttpStatus.OK;
        Person expectedPerson = new Person();
        expectedPerson.setId(expectedId);
        BDDMockito.
                given(service.show(1L))
                .willReturn(expectedPerson);

        // When
        ResponseEntity<Person> response = controller.show(expectedId);
        HttpStatus actual = response.getStatusCode();
        Person actualPerson = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedPerson, actualPerson);
    }

}
