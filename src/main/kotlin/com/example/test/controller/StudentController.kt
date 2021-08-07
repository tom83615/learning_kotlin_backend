package com.example.test.controller

import com.example.test.sql.dao.StudentDao
import com.example.test.sql.table.Student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class StudentController(@Autowired val studentDao: StudentDao) {
    /**
     * 取得 Student 所有資料
     */
    @GetMapping("/students")
    fun getStudentData(): MutableList<Student> {
        return studentDao.findAll()
    }

    /**
     * 新增 Student 資料
     */
    @PostMapping("/students")
    fun addStudentData(@RequestBody student: Student): Student {
        return studentDao.save(student)
    }

    /**
     * 利用 Student.name 查詢學生資料
     */
    @GetMapping("/students/search")
    fun getStudentByName(@RequestParam name: String): Student? {
        return studentDao.findByName(name)
    }
}