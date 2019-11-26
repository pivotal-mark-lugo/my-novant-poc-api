package com.novant.mynovant.messages

import com.novant.mynovant.providers.Provider
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api/messages")
class MessageController {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun listMessages(): List<Message> {
        logger.trace { "LIST MESSAGES" }
        val doctor = Provider(-1, "Dr. Sherry Yanez", "Neurology")
        val doctorLong = Provider(-1, "Dr. Thisisareallllllllyloooooooongnammmmmmmmmme", "Neurology")
        return listOf(
                Message(1, "Hi there", doctor, "Is the medicine working?",
                        LocalDateTime.now()),
                Message(2,
                        "Come in please for your appointment",
                        doctor,
                        "How are you feeling?",
                        LocalDateTime.parse("2019-01-20T12:34:56.789")),
                Message(3,
                        "This is regarding your last lab result on 9/9/2020 - it all looks good",
                        doctorLong,
                        "How are you feeling? I was going through your x-ray scan when I noticed that there is was a black spot in the upper thingie",
                        LocalDateTime.parse("2019-01-20T12:34:56.789"))
        )
    }
}