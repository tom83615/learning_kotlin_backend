package com.example.test.controller.impl

import com.example.test.controller.IndexController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class IndexControllerImpl : IndexController {

    override fun getHelloHtml(model: Model): String{
        return "index"
    }
}