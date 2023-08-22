package com.sananismayilov.draftproject2.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sananismayilov.draftproject2.model.Person
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PersonDao {
   @Query("SELECT * FROM Person")
   fun getAll() : Flowable<List<Person>>

   @Insert
   fun Insert(person: Person) : Completable

    @Delete
    fun Delete(person: Person) : Completable

}