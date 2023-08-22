package com.sananismayilov.draftproject2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sananismayilov.draftproject2.R
import com.sananismayilov.draftproject2.model.Person
import com.sananismayilov.draftproject2.roomdb.PersonDao
import com.sananismayilov.draftproject2.roomdb.PersonDatabase
import com.sananismayilov.draftproject2.util.Util.DB_NAME
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var personDatabase: PersonDatabase
    private lateinit var personDao: PersonDao
    private  var compositeDisposable =  CompositeDisposable()
    private lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personDatabase = Room.databaseBuilder(applicationContext,PersonDatabase::class.java,DB_NAME)
            .build()

        personDao = personDatabase.personDao()
        button = findViewById(R.id.button)
        button.setOnClickListener{
            val person = Person("James")
            insertandshow(person)
        }

    }

    private fun insertandshow(person : Person){
        compositeDisposable.add(personDao.Insert(person)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe())

        compositeDisposable.add(personDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))

    }

    private fun handleResponse(personList: List<Person>) {
        Toast.makeText(applicationContext,"${personList[1].person_id}",Toast.LENGTH_SHORT).show()
    }




    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}