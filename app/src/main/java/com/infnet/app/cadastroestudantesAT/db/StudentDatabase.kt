package com.infnet.app.cadastroestudantesAT.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.infnet.app.cadastroestudantesAT.entity.Student


@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao
}