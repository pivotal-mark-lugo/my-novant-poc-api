package com.novant.mynovant.messages

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
@AutoConfigureMockMvc
internal class MessageControllerTest {

    @Autowired
    lateinit var mvc: MockMvc;

    @Test
    @WithMockUser
    fun listMessages() {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/messages")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk
        ).andExpect(
                jsonPath("[1].subject").
                        value("Come in please for your appointment")
        ).andExpect(
                jsonPath("[1].from.name").
                        value("Dr. Sherry Yanez")
        ).andExpect(
                jsonPath("[1].text").
                        value("How are you feeling?")
        ).andExpect(
                jsonPath("[1].dateTimeSent").
                        value("2019-01-20T12:34:56.789")
        )
    }
}