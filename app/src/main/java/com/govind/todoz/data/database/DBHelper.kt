package com.govind.todoz.data.database

import com.govind.todoz.data.modal.Todo

interface DBHelper {

    suspend fun getAll(): List<Todo>
    suspend fun save(todo: Todo)
    suspend fun saveAll(todos: List<Todo>)
    suspend fun delete(todo: Todo)
}