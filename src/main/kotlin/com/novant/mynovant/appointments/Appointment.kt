package com.novant.mynovant.appointments

import com.novant.mynovant.providers.Provider
import java.time.LocalDate

data class Appointment(
    val id: Long,
    val type: String,
    val dateTime: LocalDate,
    val provider: Provider
)