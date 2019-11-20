package com.novant.mynovant.providers

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class ProviderControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    @WithMockUser
    fun listProviders() {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/providers")
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(MockMvcResultMatchers.status().isOk)
        .andExpect(
                MockMvcResultMatchers.jsonPath("[0].name").
                        value("Strange")
        )
        .andExpect(
                MockMvcResultMatchers.jsonPath("[0].practice").
                        value("Metaphysics")
        )
    }
}