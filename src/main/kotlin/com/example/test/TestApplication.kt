package com.example.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}

@RestController
class IndexController(@Autowired environment: Environment){
    // 取得 application.yml 設定的配置數值
    private final val url = environment.getProperty("spring.datasource.url");
    private final val username = environment.getProperty("spring.datasource.username");
    private final val password = environment.getProperty("spring.datasource.password");

    // 資料庫連線
    val connection: Connection = DriverManager.getConnection(url, username, password)

    @GetMapping("/")
    fun getHelloString(): String{
        return "Hello Kotlin!"
    }

    /**
     * 取得 Student 所有資料
     */
    @GetMapping("/students")
    fun getStudentData(): ArrayList<MutableMap<String, Any>> {
        // 建立 Statement 進行資料庫操作
        val statement: Statement = connection.createStatement()

        // 取得 Student 資料表所有資料
        val record: ResultSet = statement.executeQuery("SELECT * FROM Student")

        // 將 Student 資料取出並儲存在一個集合進行輸出
        val result: ArrayList<MutableMap<String, Any>> = ArrayList()
        while (record.next()) {
            val item = mutableMapOf<String, Any>()
            item["id"] = record.getInt("id")
            item["name"] = record.getString("name")
            item["email"] = record.getString("email")
            result.add(item)
        }

        return result
    }
}