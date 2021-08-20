package com.example.test.service

import com.example.test.service.impl.TodoServiceImpl
import com.example.test.sql.dao.TodoDao
import com.example.test.sql.entity.Student
import com.example.test.sql.entity.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
class TestTodoService {

    @MockBean
    lateinit var todoDao: TodoDao;

    @Autowired
    lateinit var todoServiceImpl: TodoServiceImpl;

    /**
     * 測試建立 Todos 資料
     */
    @Test
    fun shouldGetNewTodoWhenCallMethodByTodo() {
        val expectedResult = Todo(task= "testTodoItem", status = 0);
        val requestParameter = Todo(task= "testTodoItem")
        BDDMockito.given(todoDao.save(requestParameter)).willReturn(expectedResult)

        val actual: Todo = todoServiceImpl.createTodo(requestParameter)

        Assertions.assertEquals(expectedResult, actual)
    }

    /**
     * 測試取得所有 Todos 資料
     */
    @Test
    fun shouldGetAllTodosWhenCallMethod() {
        val expectedResult : MutableList<Todo> = mutableListOf()
        expectedResult.plus(Todo(task= "testTodoItem"))
        expectedResult.plus(Todo(task= "testTodoItem2"))
        expectedResult.plus(Todo(task= "中文代辦事項"))
        BDDMockito.given(todoDao.findAll()).willReturn(expectedResult)

        val actual: Iterable<Todo> = todoServiceImpl.getTodos()

        Assertions.assertEquals(expectedResult, actual)
    }

    /**
     * 測試更新 Todos 資料
     */
    @Test
    fun shouldUpdatedTodoStatusWhenCallMethodByTodo() {
        val expected = true
        val expectedUUID = UUID.randomUUID();
        val expectedResult = Todo(id= expectedUUID,task= "testTodoItem", status= 1)
        val requestParameter = Todo(id= expectedUUID,task= "testTodoItem");
        BDDMockito.given(todoDao.save(requestParameter)).willReturn(expectedResult)
        BDDMockito.given(todoDao.findById(expectedUUID)).willReturn(expectedResult)

        val actual: Boolean =todoServiceImpl.updateTodoStatus(expectedUUID.toString())
        val actualResult: Todo? = todoDao.findById(expectedUUID)

        Assertions.assertEquals(expected, actual)
        Assertions.assertEquals(expectedResult, actualResult)
    }

    /**
     * 測試刪除 Todos 資料
     */
    @Test
    fun shouldDeletedTodoWhenCallMethodByTodo() {
        val expectedResult = true
        val expectedUUID = UUID.randomUUID();
        val expectedSaveResult = Todo(id= expectedUUID,task= "testTodoItem");
        BDDMockito.given(todoDao.findById(expectedUUID)).willReturn(expectedSaveResult)

        val actual = todoServiceImpl.deleteTodo(expectedUUID.toString())

        Assertions.assertEquals(expectedResult, actual)
    }
}