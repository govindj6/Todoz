package com.govind.todoz.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.govind.todoz.data.modal.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo WHERE date = :date")
    fun getTodosByDate(date: String): LiveData<List<Todo>>

    @Insert
    suspend fun saveAll(todos: List<Todo>)

    @Insert
    suspend fun save(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("DELETE FROM todo")
    fun deleteAll()
}