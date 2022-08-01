package com.github.curriculeon.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.Entity;


public class PersonTest {
    @Test
    public void testClassSignatureAnnotations() {
        Assert.assertTrue(Person.class.isAnnotationPresent(Entity.class));
    }
    @Test
    public void testCreateJson() throws JsonProcessingException {
        ObjectMapper jsonWriter = new ObjectMapper();
        Person person = new Person();
        String json = jsonWriter.writeValueAsString(person);
    }
}
