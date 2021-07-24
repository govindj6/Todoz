package com.govind.todoz.utils

import com.govind.todoz.data.modal.Todo

open class DummyDataEngine {

    companion object Factory {
        private var todoList: MutableList<Todo> = ArrayList()

        fun getDummyTodos(): MutableList<Todo> {
            return todoList
        }

        fun addTodo(todo: Todo) {
            todoList.add(todo)
        }
    }

    open fun addDummyItems() {
        todoList.add(Todo(1, "Grocory", "Handwash, Facewash", "02-07-2021", false))
        todoList.add(
            Todo(
                2,
                "Stock Watchlist",
                "Infosys, Wipro, CDSL, Lauras lab",
                "02-07-2021",
                false
            )
        )
    }
}