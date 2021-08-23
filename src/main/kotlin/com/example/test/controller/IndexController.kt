package com.example.test.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

interface IndexController {

    @GetMapping("/")
    fun getHelloHtml(model: Model): String
}