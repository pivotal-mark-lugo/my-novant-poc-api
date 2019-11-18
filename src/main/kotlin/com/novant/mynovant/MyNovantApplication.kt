package com.novant.mynovant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class MyNovantApplication

fun main(args: Array<String>) {
	runApplication<MyNovantApplication>(*args)
}
