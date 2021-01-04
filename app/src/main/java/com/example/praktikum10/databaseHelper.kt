package com.example.praktikum10

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class databaseHelper(context: Context) : SQLiteOpenHelper (context,dbname, factory,version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table user(id integer primary key autoincrement, "+ "name varchar(50)," + "password varchar(50))") }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
    fun insertUserData(name: String, password: String): Boolean {
        val db :SQLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put("name", name)
        values.put("password", password)
        val query = "select * from user where name = '$name'"
        val cursor = db.rawQuery(query, null)
        if (cursor.count > 0) {
            cursor.close()
            db.close()
            return false
        } else {
            db.insert("user", null, values)
            cursor.close()
            db.close()
            return true } }

    fun userPresent(name: String, password: String): Boolean{
        val db = writableDatabase
        val query = "select * from user where name = '$name' and password = '$password'"
        val cursor = db.rawQuery(query,null)
        if (cursor.count <= 0){
            cursor.close()
            return false
        }else{
            cursor.close()
            return true}
        }
    companion object{
        internal val dbname = "userDB"
        internal val factory = null
        internal val version = 1
    }
}