package com.example.gamewarna2

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Ambil data username dan password yang terdaftar dari SharedPreferences
            val registeredUsername = sharedPreferences.getString("registeredUsername", "")
            val registeredPassword = sharedPreferences.getString("registeredPassword", "")

            if (username == registeredUsername && password == registeredPassword) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                // Arahkan ke MenuActivity setelah login berhasil
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()  // Menutup MainActivity agar tidak bisa kembali ke halaman login
            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
            }
        }

        // Arahkan ke RegisterActivity ketika tombol register ditekan
        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
