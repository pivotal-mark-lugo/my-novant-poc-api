package com.novant.mynovant.appointments

import com.novant.mynovant.providers.Provider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class AppointmentTest {
    private val doctor: Provider =
            Provider(-1, "Venkman", "Paranormal Investigations");

    @Test
    fun getId() {
        val appointment: Appointment = Appointment(
            -1234,
            "immunization",
            LocalDate.parse("2019-01-01"),
            doctor
        );
        Assertions.assertEquals(
            -1234,
            appointment.id
        );
    }

    @Test
    fun getDateTime() {
        var date = LocalDate.parse("2018-12-12")
        val appointment: Appointment = Appointment(
            -1234,
            "check-up",
            date,
            doctor
        );
        Assertions.assertEquals(
            LocalDate.parse("2018-12-12"),
            appointment.dateTime
        );
    }

    @Test
    fun getType() {
        val appointment: Appointment = Appointment(
            -1234,
            "knee consultation",
            LocalDate.parse("2020-01-01"),
            doctor
        );
        Assertions.assertEquals("knee consultation", appointment.type);
    }
}