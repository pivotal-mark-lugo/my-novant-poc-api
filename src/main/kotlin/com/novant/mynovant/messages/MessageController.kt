package com.novant.mynovant.messages

import com.novant.mynovant.providers.Provider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/messages")
class MessageController {
    @GetMapping
    fun listMessages(): List<Message> {
        val doctor = Provider(-1, "Dr. Sherry Yanez", "Neurology")
        return listOf(
                Message(1,
                        "Come in please for your appointment",
                        doctor,
                        "How are you feeling?",
                        LocalDateTime.parse("2020-01-20T12:34:56.789")),
                Message(2, "Hi there", doctor, "Is the medicine working?",
                        LocalDateTime.now())
        )
    }
}