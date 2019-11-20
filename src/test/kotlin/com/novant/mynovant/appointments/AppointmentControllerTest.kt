package com.novant.mynovant.appointments

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @WithMockUser
    @Test
    fun listAppointments() {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk)
        .andExpect(
            jsonPath("[0].provider.name").
                value("Snow Brenner Daws")
        )
        .andExpect(
                jsonPath("[0].type").
                        value("Immunization")
        )
    }
}