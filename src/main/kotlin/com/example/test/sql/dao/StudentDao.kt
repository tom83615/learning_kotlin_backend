package com.example.test.sql.dao

import com.example.test.sql.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface StudentDao:  JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    /**
     * 查詢符合 name 條件的學生資料
     */
    fun findByName(name: String): MutableList<Student>

    /**
     * 查詢符合 id 條件的學生資料
     */
    fun findById(id: Int): Student?
}
