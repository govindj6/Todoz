package com.govind.todoz.data.repository

import androidx.lifecycle.LiveData
import com.govind.todoz.data.database.AppDatabase
import com.govind.todoz.data.database.DBHelper
import com.govind.todoz.data.modal.Todo

class TodoRepository(private val appDatabase: AppDatabase) : DBHelper {

    override suspend fun getAll(): LiveData<List<Todo>> = appDatabase.todoDao().getAll()

    override suspend fun getTodosByDate(date: String): LiveData<List<Todo>> =
        appDatabase.todoDao().getTodosByDate(date)

    override suspend fun save(todo: Todo) = appDatabase.todoDao().save(todo)

    override suspend fun saveAll(todos: List<Todo>) = appDatabase.todoDao().saveAll(todos)

    override suspend fun update(todo: Todo) = appDatabase.todoDao().update(todo)

    override suspend fun delete(todo: Todo) = appDatabase.todoDao().delete(todo)

    override suspend fun deleteAll() = appDatabase.todoDao().deleteAll()

}