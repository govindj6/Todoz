package com.govind.todoz.data.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "isRead") val isRead: Boolean
) {
    constructor(title: String, desc: String, date: String, isRead: Boolean) : this(
        0,
        title,
        desc,
        date,
        isRead
    )
}