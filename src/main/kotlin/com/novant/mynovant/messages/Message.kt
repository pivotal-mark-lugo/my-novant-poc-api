package com.novant.mynovant.messages

import com.novant.mynovant.providers.Provider
import java.time.LocalDateTime

data class Message(
        val subject: String,
        val from: Provider,
        val text: String,
        val dateTimeSent: LocalDateTime
)