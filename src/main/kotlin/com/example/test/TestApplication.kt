package com.example.test

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}

@RestController
class IndexController(){
    @GetMapping("/")
    fun getHelloString(): String{
        return "Hello Kotlin!"
    }
}