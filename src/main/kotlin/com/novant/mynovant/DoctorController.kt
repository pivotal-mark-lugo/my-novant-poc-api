package com.novant.mynovant

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/api")
class DoctorController {
    @GetMapping("")
    fun listApiOperations() = listOf("/doctors")

    @GetMapping("/doctors")
    fun listDoctors(): List<Doctor> {
        println("LIST DOCS")
        return listOf(
                Doctor(1, "Strange", "Metaphysics"),
                Doctor(2, "Who", "Chronodynamics"),
                Doctor(3, "Challenger", "Geotraumatics"),
                Doctor(4, "Jekyll", "Metamorphology"),
                Doctor(5, "Mabuse", "Hypnosis"),
                Doctor(6, "Lecter", "Psychiatry"),
                Doctor(7, "Venkman", "Paranormal Investigation"),
                Doctor(8, "Moreau", "Genotherapy"),
                Doctor(9, "Jones", "Archaeology"),
                Doctor(10, "Frankenstein", "Monstronomy"),
                Doctor(11, "Crusher", "General Practitioner"),
                Doctor(12, "Name", "Emergency Medical Hologram Mark I"),
                Doctor(13, "Katz", "Therapist")
        )
    }
}