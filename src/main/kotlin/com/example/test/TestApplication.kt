package com.example.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import java.sql.Connection
import java.sql.DriverManager

@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}

@Controller
class IndexController(@Autowired environment: Environment){
    // 取得 application.yml 設定的配置數值
    private final val url = environment.getProperty("spring.datasource.url");
    private final val username = environment.getProperty("spring.datasource.username");
    private final val password = environment.getProperty("spring.datasource.password");

    // 資料庫連線
    val connection: Connection = DriverManager.getConnection(url, username, password)

    @RequestMapping("/")
    fun getHelloString(): String{
        return "index"
    }
}