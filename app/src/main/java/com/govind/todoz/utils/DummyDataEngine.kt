package com.govind.todoz.utils

import com.govind.todoz.entities.Todo

class DummyDataEngine {

    companion object Factory {
        fun getDummyTodos(): MutableList<Todo> {
            var todos: MutableList<Todo> = ArrayList()
            todos.add(Todo(1, "Grocory", "Handwash, Facewash", "02-07-2021", false))
            todos.add(
                Todo(
                    2,
                    "Stock Watchlist",
                    "Infosys, Wipro, CDSL, Lauras lab",
                    "02-07-2021",
                    false
                )
            )
            return todos
        }
    }
}