package com.example.praktikum10.catatan

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var alamat: String,
    var deskripsi: String,
    var tanggal: String,
    var jam: String ) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}