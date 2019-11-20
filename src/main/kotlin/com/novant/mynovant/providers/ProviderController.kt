package com.novant.mynovant.providers

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/providers")
class ProviderController {
    private val logger = KotlinLogging.logger {}

    @GetMapping
    fun listProviders(): List<Provider> {
        logger.trace { "LIST PROVIDERS" }
        return listOf(
                Provider(1, "Strange", "Metaphysics"),
                Provider(2, "Who", "Chronodynamics"),
                Provider(3, "Challenger", "Geotraumatics"),
                Provider(4, "Jekyll", "Metamorphology"),
                Provider(5, "Mabuse", "Hypnosis"),
                Provider(6, "Lecter", "Psychiatry"),
                Provider(7, "Venkman", "Paranormal Investigation"),
                Provider(8, "Moreau", "Genotherapy"),
                Provider(9, "Jones", "Archaeology"),
                Provider(10, "Frankenstein", "Monstronomy"),
                Provider(11, "Crusher", "General Practitioner"),
                Provider(12, "Name", "Emergency Medical Hologram Mark I"),
                Provider(13, "Katz", "Therapist")
        )
    }
}