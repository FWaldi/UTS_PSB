package com.example.gamewarna2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    lateinit var etNewUsername: EditText
    lateinit var etNewPassword: EditText
    lateinit var btnRegister: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNewUsername = findViewById(R.id.etNewUsername)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnRegister = findViewById(R.id.btnRegister)

        sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE)

        btnRegister.setOnClickListener {
            val newUsername = etNewUsername.text.toString()
            val newPassword = etNewPassword.text.toString()

            if (newUsername.isNotEmpty() && newPassword.isNotEmpty()) {
                // Simpan username dan password di SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("registeredUsername", newUsername)
                editor.putString("registeredPassword", newPassword)
                editor.apply()

                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                // Kembali ke halaman login setelah berhasil registrasi
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()  // Menutup RegisterActivity agar tidak bisa kembali
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}