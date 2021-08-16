package com.example.test.controller

import com.example.test.service.impl.StudentServiceImpl
import com.example.test.sql.entity.Student
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*

@WebMvcTest(StudentController::class)
class TestStudentController {

    @MockBean
    lateinit var studentServiceImpl: StudentServiceImpl

    @Autowired
    lateinit var mockMvc: MockMvc

    private val objectMapper = ObjectMapper()

    /**
     * 建立 Student 資料
     * (Post)/api/students
     */
    @Test
    fun shouldGetNewStudentWhenCallMethodByStudent() {
        val expectedResult = Student( 1, "Devin", "devin@gmail.com")
        val requestParameter = Student( name = "Devin", email = "devin@gmail.com")
        given(studentServiceImpl.addStudent(requestParameter)).willReturn(expectedResult)

        mockMvc.perform(
            post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParameter))
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResult)))
    }

    /**
     * 測試取得所有 Student 資料
     * (Get)/api/students
     */
    @Test
    fun shouldGetAllStudentWhenCallMethod() {
        val expectedResult : MutableList<Student> = mutableListOf()
        expectedResult.add(Student(1, "Tester", "test@gmail.com"))
        expectedResult.add(Student(2, "Tom", "tom83615@gmail.com"))
        given(studentServiceImpl.findAllStudent()).willReturn(expectedResult)

        mockMvc.perform(
            get("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResult)))
    }

    /**
     * 測試取得單一 Student 資料 By id
     * (Post)/api/students/access
     */
    @Test
    fun shouldGetOneStudentWhenCallMethodById() {
        val expectedResult = Student(1, "Edited", "edit@gmail.com")
        given(studentServiceImpl.findByStudentId(1)).willReturn(expectedResult)

        mockMvc.perform(
            post("/api/students/access?id=1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResult)))
    }

    /**
     * 測試取得單一 Student 資料 By name
     * (Post)/api/students/search
     */
    @Test
    fun shouldGetOneStudentWhenCallMethodByName() {
        val expectedResult : MutableList<Student> = mutableListOf()
        expectedResult.add(Student(1, "Tester", "test@gmail.com"))
        given(studentServiceImpl.findByStudentName("Tester")).willReturn(expectedResult)

        mockMvc.perform(
            post("/api/students/search")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "Tester")
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResult)))
    }

    /**
     * 更新 Student 資料
     * (Put)/api/students/{id}
     */
    @Test
    fun shouldUpdatedStudentWhenCallMethodByStudent() {
        val expectedResult = Student(1, "Tester", "test@gmail.com")
        val requestParameter = Student(1, "Edited", "edit@gmail.com")
        given(studentServiceImpl.findByStudentId(1)).willReturn(requestParameter)
        given(studentServiceImpl.updateStudent(requestParameter)).willReturn(expectedResult)

        mockMvc.perform(
            put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestParameter))
        ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().string(objectMapper.writeValueAsString(expectedResult)))
    }

    /**
     * 刪除 Student 資料 (成功)
     * (Delete)/api/students/{id}
     */
    @Test
    fun shouldGetIsNotContentStatusWhenDeleteSuccess() {
        val expectedResult = true
        given(studentServiceImpl.deleteStudent(1)).willReturn(expectedResult)

        mockMvc.perform(
            delete("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent)
    }

    /**
     * 刪除 Student 資料 (失敗)
     * (Delete)/api/students/{id}
     */
    @Test
    fun shouldGetBadRequestStatusWhenDeleteFailed() {
        val expectedResult = false
        given(studentServiceImpl.deleteStudent(1)).willReturn(expectedResult)

        mockMvc.perform(
            delete("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }
}