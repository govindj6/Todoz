package com.govind.todoz.data.database

import com.govind.todoz.data.modal.Todo

class DBHelperImpl(private val appDatabase: AppDatabase) : DBHelper {

    override suspend fun getAll(): List<Todo> = appDatabase.todoDao().getAll()

    override suspend fun save(todo: Todo) = appDatabase.todoDao().save(todo)

    override suspend fun saveAll(todos: List<Todo>) = appDatabase.todoDao().saveAll(todos)

    override suspend fun delete(todo: Todo) = appDatabase.todoDao().delete(todo)


}