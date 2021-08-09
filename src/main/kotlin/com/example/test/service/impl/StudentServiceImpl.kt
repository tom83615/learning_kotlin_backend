package com.example.test.service.impl

import com.example.test.service.StudentService
import com.example.test.sql.dao.StudentDao
import com.example.test.sql.entity.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentServiceImpl(@Autowired val studentDao: StudentDao) : StudentService {
    override fun findAllStudent(): MutableList<Student> = studentDao.findAll()

    override fun addStudent(student: Student): Student =
        Student(
            name = student.name.trim(),
            email = student.email.trim()
        ).run {
            return studentDao.save(this)
        }

    override fun findByStudentId(id: Long): Optional<Student> = studentDao.findById(id)

    override fun findByStudentName(name: String): List<Student> = studentDao.findByName(name)

    override fun updateStudent(student: Student): Student =
        Student(
            id = student.id,
            name = student.name.trim(),
            email = student.email.trim()
        ).run {
            return studentDao.save(this)
        }

    override fun updateStudentEmail(student: Student): Student =
        Student(
            id = student.id,
            name = student.name,
            email = student.email.trim()
        ).run {
            return studentDao.save(this)
        }

    override fun deleteStudent(student: Student): Unit = studentDao.delete(student)

}