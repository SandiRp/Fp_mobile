package com.example.praktikum10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.daftar.*
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login.btnlogin
import kotlinx.android.synthetic.main.user_container.*

class UserAction : AppCompatActivity() {
    lateinit var handler: databaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_container)
        handler = databaseHelper(this)
        showHome()
        btnsimpan.setOnClickListener {
            if (inputEmail2.text.toString() == "" || inputPassword2.text.toString() == "") {
                Toast.makeText(this, "Harap Mengisi Username dan Password!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (handler.insertUserData(
                        inputEmail2.text.toString(),
                        inputPassword2.text.toString()
                    )
                ) {
                    Toast.makeText(this, "Pendaftaran Anda Telah Sukses!", Toast.LENGTH_SHORT)
                        .show()
                    inputEmail2.text?.clear()
                    inputPassword2.text?.clear()
                } else {
                    Toast.makeText(
                        this,
                        "Username yang Anda Masukkan Tidak Tersedia!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    inputPassword2.text?.clear()
                }
            }
        }


        btnlogin.setOnClickListener {
            if (inputEmail.text.toString() == "" || inputPassword.text.toString() == "") {
                Toast.makeText(this, "Harap Mengisi Username dan Password Anda", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (handler.userPresent(
                        inputEmail.text.toString(),
                        inputPassword.text.toString()
                    )
                ) {
                    Toast.makeText(this, "Berhasil!", Toast.LENGTH_SHORT).show()
                    inputEmail.text?.clear()
                    inputPassword.text?.clear()
                    val intentMain = Intent(this, MainActivity::class.java)
                    startActivity(intentMain)
                } else {
                    Toast.makeText(
                        this,
                        "Username atau Password yang Anda Masukkan Salah!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    inputPassword.text?.clear()
                }
            }
        }

        btndaftar.setOnClickListener {
            inputEmail.text?.clear()
            inputPassword.text?.clear()
            showRegistration()
        }

        btnlogin2.setOnClickListener{
            inputEmail2.text?.clear()
            inputPassword2.text?.clear()
            showLogin()
        }

    }


    private fun showRegistration() {
        registration_layout.visibility = View.VISIBLE
        login_layout.visibility = View.GONE

    }

    private fun showLogin() {
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.VISIBLE

    }

    private fun showHome() {
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.VISIBLE

    }

}
