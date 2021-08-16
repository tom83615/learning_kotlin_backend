package com.example.test.controller

import com.example.test.sql.entity.Student

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface StudentController {
    /**
     * (GET:/students)
     * 取得 Student 所有資料
     */
    @GetMapping("/students")
    fun getStudentData(): MutableList<Student>

    /**
     * (POST:/students)
     * 新增 Student 資料
     */
    @PostMapping("/students")
    fun addStudentData(@RequestBody student: Student) : Student

    /**
     * (POST:/students/access?{id})
     * 利用 id 查詢學生資料
     */
    @PostMapping("/students/access")
    fun getStudentById(@RequestParam id: Int) : ResponseEntity<Student?>

    /**
     * (POST:/students/search?{name})
     * 利用 name 查詢學生資料
     */
    @PostMapping("/students/search")
    fun getStudentByName(@RequestParam name: String) : ResponseEntity<List<Student>>

    /**
     * (PUT:/students/{id})
     * 修改學生資料
     */
    @PutMapping("/students/{id}")
    fun updateStudent(@PathVariable id: Int, @RequestBody student: Student) : ResponseEntity<Student?>

    /**
     * (DELETE:/students/{id})
     * 刪除學生資料
     */
    @DeleteMapping("/students/{id}")
    fun deleteStudent(@PathVariable id: Int): ResponseEntity<Any>
}