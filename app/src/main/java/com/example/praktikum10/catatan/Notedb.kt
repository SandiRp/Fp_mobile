package com.example.praktikum10.catatan

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1)
abstract class Notedb : RoomDatabase() {
    abstract fun noteDao():NoteDao
    companion object{
        private var instance:Notedb? = null
        fun getInstance(context: Context):Notedb?{
            if (instance == null){
                synchronized(Notedb::class){
                    instance = Room.databaseBuilder(context.applicationContext,
                        Notedb::class.java,"note_database").fallbackToDestructiveMigration()
                        .addCallback(roomCallback).build()
                }
            }
            return instance
        }
        fun destroyInstance(){
            instance = null
        }
        private val roomCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }
    class PopulateDbAsyncTask(db:Notedb?) : AsyncTask<Unit, Unit, Unit>(){
        private val noteDao = db?.noteDao()

        override fun doInBackground(vararg p0: Unit?) {
            noteDao?.insert(Note("Square Ballroom", "Jl.Tegalsari", "Datang Tepat Waktu", "10-01-2021","15.00"))
        }
    }
}