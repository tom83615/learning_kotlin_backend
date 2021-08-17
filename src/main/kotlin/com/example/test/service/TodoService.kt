package com.example.test.service

import com.example.test.sql.entity.Todo

interface TodoService {

    /**
     * 取得所有 Todos 資料
     */
    fun getTodos(): Iterable<Todo>

    /**
     * 建立 Todos 資料
     */
    fun createTodo(todo: Todo): Todo

    /**
     * 更新 Todos 狀態
     */
    fun updateTodoStatus(id: String): Boolean

    /**
     * 刪除 Todos 資料
     */
    fun deleteTodo(id: String): Boolean
}