package com.novant.mynovant.appointments

import com.novant.mynovant.facilties.Facility
import com.novant.mynovant.providers.Provider
import java.time.LocalDateTime

data class Appointment(
        val id: Long,
        val type: String,
        val dateTime: LocalDateTime,
        val provider: Provider,
        val facility: Facility
)