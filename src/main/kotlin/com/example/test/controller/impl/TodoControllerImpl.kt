package com.example.test.controller.impl

import com.example.test.controller.TodoController
import com.example.test.service.TodoService
import com.example.test.sql.entity.Todo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model

@Controller
class TodoControllerImpl(@Autowired val todoService: TodoService): TodoController {
    override fun getTodos(model: Model): String {
        model.addAttribute("todolist", todoService.getTodos());
        model.addAttribute("todoObject", Todo())
        return "todo"
    }

    override fun createTodo(todo: Todo): String {
        todoService.createTodo(todo)

        return "redirect:/todos"
    }

    override fun updateTodoStatus(id: String): ResponseEntity<Any>{
        todoService
            .updateTodoStatus(id)
            .run {
                if (!this) {
                    return ResponseEntity<Any>(null, HttpStatus.NOT_FOUND)
                }
                return ResponseEntity<Any>(null, HttpStatus.OK)
            }
    }

    override fun deleteTodo(id: String): ResponseEntity<Any> {
        todoService
            .deleteTodo(id)
            .run {
                if (!this) {
                    return ResponseEntity<Any>(null, HttpStatus.NOT_FOUND)
                }
                return ResponseEntity<Any>(null, HttpStatus.OK)
            }
    }

}