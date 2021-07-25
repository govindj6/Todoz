package com.govind.todoz.data.database

import androidx.lifecycle.LiveData
import com.govind.todoz.data.modal.Todo

interface DBHelper {

    suspend fun getAll(): LiveData<List<Todo>>
    suspend fun getTodosByDate(date: String): LiveData<List<Todo>>
    suspend fun save(todo: Todo)
    suspend fun saveAll(todos: List<Todo>)
    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)
    suspend fun deleteAll()
}