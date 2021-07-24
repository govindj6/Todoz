package com.govind.todoz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.govind.todoz.data.modal.Todo

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao


}