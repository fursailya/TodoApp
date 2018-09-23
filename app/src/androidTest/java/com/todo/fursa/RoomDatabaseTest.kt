package com.todo.fursa

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.todo.fursa.room.dao.TodoDAO
import com.todo.fursa.room.database.AppDatabase
import com.todo.fursa.room.model.Todo
import org.junit.After
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before


@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {
    private lateinit var todoDAO: TodoDAO
    private lateinit var appDatabase: AppDatabase
    private lateinit var context: Context

    @Before
    fun createAppDatabase() {
        context = InstrumentationRegistry.getTargetContext()!!
        appDatabase = AppDatabase.newInstance(context)!!
        todoDAO = appDatabase.todoDAO
    }

    @Test
    fun deleteTest() {
        val deleteRes = todoDAO.delete(1)
        Assert.assertNotNull(deleteRes)
    }

    @Test
    fun sizeTest() {
        todoDAO.size().observeForever {
            Assert.assertTrue(it != null)
        }
    }

    @Test
    fun saveTest() {
        val todo = Todo(2, "Test title 2", "Test description 2", System.currentTimeMillis())
        todoDAO.save(todo)
        todoDAO.selectById(2).observeForever {
            Assert.assertEquals(todo.title, it!!.title)
        }
    }

    @After
    fun closeAppDatabaseConnection() {
        appDatabase.close()
    }

}
