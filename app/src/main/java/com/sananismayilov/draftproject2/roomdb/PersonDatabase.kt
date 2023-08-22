package com.sananismayilov.draftproject2.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sananismayilov.draftproject2.model.Person

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao() : PersonDao
}