package com.novant.mynovant.appointments

import com.novant.mynovant.facilties.Facility
import com.novant.mynovant.providers.Provider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class AppointmentTest {
    private val doctor: Provider =
            Provider(-1, "Venkman", "Paranormal Investigations");

    private val facility: Facility =
            Facility("Piedmont Pediatric")

    private val appointment: Appointment = Appointment(
            -1234,
            "immunization",
            LocalDateTime.parse("2019-01-01T12:34:56.789"),
            doctor,
            facility
    );

    @Test
    fun getId() {
        Assertions.assertEquals(
            -1234,
            appointment.id
        );
    }

    @Test
    fun getDateTime() {
        Assertions.assertEquals(
            LocalDateTime.parse("2019-01-01T12:34:56.789"),
            appointment.dateTime
        );
    }

    @Test
    fun getType() {
        Assertions.assertEquals("immunization", appointment.type);
    }
}