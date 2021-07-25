package com.govind.todoz.data.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "isRead") val isRead: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)