package com.example.test.sql.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.*

import javax.persistence.*;

@Entity
@Table
@EntityListeners(AuditingEntityListener::class)
@EnableJpaAuditing
data class Todo(
        @Id
        val id: UUID = UUID.randomUUID(),

        val task: String = "",

        var status: Int = 0,

        @CreatedDate
        @Column(updatable = false, nullable = false)
        val createTime: Date = Date(),

        @LastModifiedDate
        @Column(nullable = false)
        val updateTime: Date = Date()
)
