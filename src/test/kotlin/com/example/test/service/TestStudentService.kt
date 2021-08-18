package com.example.test.service

import com.example.test.service.impl.StudentServiceImpl
import com.example.test.sql.dao.StudentDao
import com.example.test.sql.entity.Student
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class TestStudentService {

    @MockBean
    lateinit var studentDao: StudentDao

    @Autowired
    lateinit var studentServiceImpl: StudentServiceImpl

    /**
     * 測試建立 Student 資料
     */
    @Test
    fun shouldGetNewStudentWhenCallMethodByStudent() {
        val expectedResult = Student( 1, "Tester", "test@gmail.com")
        val requestParameter = Student( name = "Tester", email = "test@gmail.com")
        given(studentDao.save(requestParameter)).willReturn(expectedResult)

        val actual : Student = studentServiceImpl.addStudent(requestParameter)

        assertEquals(expectedResult, actual)
    }

    /**
     * 測試取得所有 Student 資料
     */
    @Test
    fun shouldGetAllStudentWhenCallMethod() {
        val expectedResult : MutableList<Student> = mutableListOf()
        expectedResult.add(Student(1, "Tester", "test@gmail.com"))
        expectedResult.add(Student(2, "Tom", "tom83615@gmail.com"))
        given(studentDao.findAll()).willReturn(expectedResult)

        val actual : MutableList<Student> = studentServiceImpl.findAllStudent()

        assertEquals(expectedResult, actual)
    }

    /**
     * 測試利用 id 取得 Student 資料
     */
    @Test
    fun shouldGetOneStudentWhenCallMethodById() {
        val expectedResult = Student(1, "Tester", "test@gmail.com")
        given(studentDao.findById(1)).willReturn(expectedResult)

        val actual : Student? = studentServiceImpl.findByStudentId(1)

        assertEquals(expectedResult, actual)
    }

    /**
     * 測試利用 Name 欄位取得 Student 資料
     */
    @Test
    fun shouldGetStudentsWhenCallMethodByName() {
        val expectedResult : MutableList<Student> = mutableListOf<Student>()
        expectedResult.add(Student(1, "Tester", "test@gmail.com"))
        given(studentDao.findByName("Tester")).willReturn(expectedResult)

        val actual : MutableList<Student> = studentServiceImpl.findByStudentName("Tester")

        assertEquals(expectedResult, actual)
    }

    /**
     * 測試更新 Student 資料
     */
    @Test
    fun shouldUpdatedStudentWhenCallMethodByStudent() {
        val expectedResult = Student(1, "Tester", "test@gmail.com")
        val requestParameter = Student(1, "Edited", "edit@gmail.com")
        given(studentDao.save(requestParameter)).willReturn(expectedResult)

        val actual : Student = studentServiceImpl.updateStudent(requestParameter)

        assertEquals(expectedResult, actual)
    }

    /**
     * 測試刪除 Student 資料
     */
    @Test
    fun shouldDeletedStudentWhenCallMethodByStudent() {
        val expectedResult = true
        val expectedSaveResult = Student(1, "Tester", "test@gmail.com")
        given(studentDao.findById(1)).willReturn(expectedSaveResult)

        val actual = studentServiceImpl.deleteStudent(1)

        assertEquals(expectedResult, actual)
    }
}
