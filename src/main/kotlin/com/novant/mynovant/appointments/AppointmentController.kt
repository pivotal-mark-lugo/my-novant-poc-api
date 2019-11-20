package com.novant.mynovant.appointments

import com.novant.mynovant.providers.Provider
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/appointments")
class AppointmentController {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun listAppointments(): List<Appointment> {
        logger.trace { "LIST APPOINTMENTS" }
        val snow = Provider(1, "Snow Brenner Daws", "Endocrinology")
        return listOf(
            Appointment(1, "Immunization", LocalDate.parse("2020-01-01"), snow)
        )
    }
}