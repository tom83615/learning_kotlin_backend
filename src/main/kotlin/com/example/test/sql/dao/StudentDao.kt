package com.example.test.sql.dao

import com.example.test.sql.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface StudentDao: JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>{

    fun findByName(name: String): List<Student>
}
