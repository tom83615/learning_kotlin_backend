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
@RequestMapping("/api")
class StudentControllerImpl(@Autowired val studentService: StudentService) : StudentController {

    override fun getStudentData(): MutableList<Student> = studentService.findAllStudent()

    override fun addStudentData(student: Student): Student = studentService.addStudent(student)

    override fun getStudentByName(name: String): ResponseEntity<List<Student>> =
        studentService
            .findByStudentName(name)
            .let {
                return ResponseEntity(it, HttpStatus.OK)
            }

    override fun updateStudent(id: Long, student: Student): ResponseEntity<Student?> =
        studentService
            .findByStudentId(id)
            .run {
                this.get() ?: return ResponseEntity<Student?>(null, HttpStatus.NOT_FOUND)
            }
            .run {
                Student(
                    id = id,
                    name = student.name,
                    email = student.email
                )
            }
            .run {
                return ResponseEntity<Student?>(studentService.updateStudentEmail(this), HttpStatus.OK)
            }

    override fun deleteStudent(id: Long): ResponseEntity<Any> =
        studentService
            .findByStudentId(id)
            .run {
                this.get() ?: return ResponseEntity<Any>(null, HttpStatus.NOT_FOUND)
            }
            .run {
                return ResponseEntity<Any>(studentService.deleteStudent(id), HttpStatus.NO_CONTENT)
            }

}