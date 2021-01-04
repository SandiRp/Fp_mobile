package com.example.praktikum10

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object { const val EXTRA_ID = "com.piusanggoro.notesapp.EXTRA_ID"
        const val EXTRA_JUDUL = "com.piusanggoro.notesapp.EXTRA_JUDUL"
        const val EXTRA_DESKRIPSI = "com.piusanggoro.notesapp.EXTRA_DESKRIPSI"
        const val EXTRA_ALAMAT = "com.piusanggoro.notesapp.EXTRA_ALAMAT"
        const val EXTRA_TANGGAL = "com.piusanggoro.notesapp.EXTRA_TANGGAL"
        const val EXTRA_JAM = "com.piusanggoro.notesapp.EXTRA_JAM"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_black_24dp)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Informasi"
            edit_text_title.setText(intent.getStringExtra(EXTRA_JUDUL))
            edit_text_description.setText(intent.getStringExtra(EXTRA_DESKRIPSI))
            edit_text_alamat.setText(intent.getStringExtra(EXTRA_ALAMAT))
            edit_text_tgl.setText(intent.getStringExtra(EXTRA_TANGGAL))
            edit_text_jam.setText(intent.getStringExtra(EXTRA_JAM))
        } else {
            title = "Tambah Informasi"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun saveNote() {
        if (edit_text_title.text.toString().trim().isBlank() || edit_text_description.text.toString().trim().isBlank() ||
                edit_text_alamat.text.toString().trim().isBlank() || edit_text_tgl.text.toString().trim().isBlank() ){
            Toast.makeText(this, "Informasi Kurang Lengkap!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_JUDUL, edit_text_title.text.toString())
            putExtra(EXTRA_DESKRIPSI, edit_text_description.text.toString())
            putExtra(EXTRA_ALAMAT, edit_text_alamat.text.toString())
            putExtra(EXTRA_TANGGAL, edit_text_tgl.text.toString())
            putExtra(EXTRA_JAM, edit_text_jam.text.toString())
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}