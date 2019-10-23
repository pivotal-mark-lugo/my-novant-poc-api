package com.novant.mynovant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyNovantApplication

fun main(args: Array<String>) {
	runApplication<MyNovantApplication>(*args)
}
