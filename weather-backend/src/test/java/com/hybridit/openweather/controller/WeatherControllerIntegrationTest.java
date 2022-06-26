package com.hybridit.openweather.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc()
public class WeatherControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testAvgTemp() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/weather")
                        .param("cityIds", "2643743")
                        .param("from", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .param("to",LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray())
                .andExpect(jsonPath("$[0].cityId", Matchers.is(2643743)))
                .andExpect(jsonPath("$.[*].avgTemperature").isNotEmpty())
                .andReturn();
        assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }
}
