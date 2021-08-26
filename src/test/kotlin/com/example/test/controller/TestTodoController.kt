package com.example.test.controller

import com.example.test.service.impl.TodoServiceImpl
import com.example.test.sql.entity.Todo
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@WebMvcTest(TodoController::class)
class TestTodoController {

    @MockBean
    lateinit var todoServiceImpl: TodoServiceImpl

    @Autowired
    lateinit var mockMvc: MockMvc

    private val objectMapper = ObjectMapper()

    /**
     * (POST:/todos)
     * 建立 Todos 資料
     * 新增後會導頁
     */
    @Test
    fun shouldGetNewTodosPageWhenCallMethodByAddTodo() {
        val expectedResult = Todo(task= "testTodoItem")
        val requestParameter = Todo(task= "testTodoItem")
        BDDMockito.given(todoServiceImpl.createTodo(requestParameter)).willReturn(expectedResult)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParameter))
        ).andExpect(MockMvcResultMatchers.status().isFound)
    }

    /**
     * (Get)/todos
     * 測試 todos page
     */
    @Test
    fun shouldGetTodosPageWhenCallMethod() {
        val expectedResult : MutableList<Todo> = mutableListOf()
        expectedResult.add(Todo(task= "testTodoItem"))
        expectedResult.add(Todo(task= "中文代辦事項"))
        BDDMockito.given(todoServiceImpl.getTodos()).willReturn(expectedResult)

        mockMvc.perform(
            MockMvcRequestBuilders.get("/todos")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("todo"))
            .andExpect(MockMvcResultMatchers.model().attribute("todolist", todoServiceImpl.getTodos()))
    }

    /**
     * (PUT:/todos/{id})
     * 測試變更 todos 狀態成功
     */
    @Test
    fun shouldUpdatedTodoStatusWhenCallMethodByIdSuccess() {
        val id = UUID.randomUUID().toString()
        val status = true
        BDDMockito.given(todoServiceImpl.updateTodoStatus(id)).willReturn(status)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/todos/$id")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    /**
     * (PUT:/todos/{id})
     * 測試變更 todos 狀態失敗
     */
    @Test
    fun shouldUpdatedTodoStatusWhenCallMethodByIdFailed() {
        val id = UUID.randomUUID().toString()
        val status = false
        BDDMockito.given(todoServiceImpl.updateTodoStatus(id)).willReturn(status)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/todos/$id")
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    /**
     * (DELETE:/todos/{id})
     * 測試刪除 todos 資料成功
     */
    @Test
    fun shouldDeleteTodoWhenCallMethodByIdSuccess() {
        val id = UUID.randomUUID().toString()
        val status = true
        BDDMockito.given(todoServiceImpl.deleteTodo(id)).willReturn(status)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/todos/$id")
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    /**
     * (DELETE:/todos/{id})
     * 測試刪除 todos 資料失敗
     */
    @Test
    fun shouldDeleteTodoWhenCallMethodByIdFailed() {
        val id = UUID.randomUUID().toString()
        val status = false
        BDDMockito.given(todoServiceImpl.deleteTodo(id)).willReturn(status)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/todos/$id")
        ).andExpect(MockMvcResultMatchers.status().isNotFound)

    }
}