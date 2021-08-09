package com.example.test.sql.entity

import javax.persistence.*

@Entity
@Table
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,

    @Column
    val name: String = "",

    @Column
    val email: String = ""
)