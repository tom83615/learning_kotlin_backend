package com.example.test.controller

import com.example.test.sql.dao.StudentDao
import com.example.test.sql.table.Student

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    /**
     * 修改學生資料
     */
    @PutMapping("/students/{id}")
    fun updateStudent(@PathVariable id: Long, @RequestBody student: Student): ResponseEntity<Student?>
            = studentDao
        .findById(id)
        .run {
            this ?: return ResponseEntity<Student?>(null, HttpStatus.NOT_FOUND)
        }
        .run{
            Student(
                id = id,
                name = student.name,
                email = student.email
            )
        }
        .run {
            return ResponseEntity<Student?>(studentDao.save(this), HttpStatus.OK)
        }

    /**
     * 刪除學生資料
     */
    @DeleteMapping("/students/{id}")
    fun deleteStudent(@PathVariable id: Long): ResponseEntity<Any> =
        studentDao
            .findById(id)
            .run {
                this ?: return ResponseEntity<Any>(null, HttpStatus.NOT_FOUND)
            }
            .run {
                return ResponseEntity<Any>(studentDao.deleteById(id), HttpStatus.NO_CONTENT)
            }
}