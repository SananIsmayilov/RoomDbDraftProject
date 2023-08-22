package com.sananismayilov.draftproject2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @ColumnInfo(name = "person_name")
    var person_name : String
){
    @PrimaryKey(autoGenerate = true)
    var person_id  = 0

}
