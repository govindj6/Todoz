package com.govind.todoz.entities

data class Todo(
    val id: Int,
    val title: String,
    val desc: String,
    val date: String,
    val isRead: Boolean
) {

}