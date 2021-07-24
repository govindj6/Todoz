package com.govind.todoz.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.govind.todoz.data.modal.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<Todo>

    @Insert
    suspend fun saveAll(todos: List<Todo>)

    @Insert
    suspend fun save(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

}