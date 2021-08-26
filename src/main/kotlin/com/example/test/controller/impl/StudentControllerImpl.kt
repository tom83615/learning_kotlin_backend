package com.example.test.controller.impl

import com.example.test.controller.StudentController
import com.example.test.service.StudentService
import com.example.test.sql.entity.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StudentControllerImpl(@Autowired val studentService: StudentService) : StudentController {

    override fun getStudentData(): MutableList<Student> = studentService.findAllStudent()

    override fun addStudentData(student: Student): Student = studentService.addStudent(student)

    override fun getStudentById(id: Int): ResponseEntity<Student?> =
        studentService
            .findByStudentId(id)
            .run {
                return ResponseEntity<Student?>(this, HttpStatus.OK)
            }

    override fun getStudentByName(name: String): ResponseEntity<List<Student>> =
        studentService
            .findByStudentName(name)
            .let {
                return ResponseEntity(it, HttpStatus.OK)
            }

    override fun updateStudent(id: Int, student: Student): ResponseEntity<Student?> =
        studentService
            .findByStudentId(id)
            .run {
                this ?: return ResponseEntity<Student?>(null, HttpStatus.NOT_FOUND)
            }
            .run {
                val newStudent = studentService.updateStudent(this)
                return ResponseEntity<Student?>(newStudent, HttpStatus.OK)
            }

    override fun deleteStudent(id: Int): ResponseEntity<Any> =
        studentService
            .deleteStudent(id)
            .run {
                if (!this) {
                    return ResponseEntity<Any>(null, HttpStatus.BAD_REQUEST)
                }
                return ResponseEntity<Any>(null, HttpStatus.NO_CONTENT)
            }

}