package com.example.test.controller

import com.example.test.sql.entity.Todo
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

interface TodoController {

    /**
     * (GET:/todos)
     * 取得 todos 所有資料
     */
    @GetMapping("/todos")
    fun getTodos(model: Model): String

    /**
     * (POST:/todos)
     * 新增 todos 資料
     */
    @PostMapping("/todos")
    fun createTodo(@ModelAttribute todo: Todo): String

    /**
     * (PUT:/todos/{id})
     * 變更 todos 事項
     */
    @PutMapping("/todos/{id}")
    @ResponseBody
    fun updateTodoStatus(@PathVariable id: String)

    /**
     * (DELETE:/todos/{id})
     * 刪除 todos 資料
     */
    @DeleteMapping("/todos/{id}")
    @ResponseBody
    fun deleteTodo(@PathVariable id: String)
}