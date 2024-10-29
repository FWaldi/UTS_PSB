package com.example.gamewarna2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    lateinit var btnPlay: Button
    lateinit var btnScoreboard: Button
    lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnPlay = findViewById(R.id.btnPlay)
        btnScoreboard = findViewById(R.id.btnScoreboard)
        btnExit = findViewById(R.id.btnExit)

        // Tombol untuk memulai game
        btnPlay.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        // Tombol untuk membuka skorboard
        btnScoreboard.setOnClickListener {
            val intent = Intent(this, ScoreboardActivity::class.java)
            startActivity(intent)
        }

        // Tombol untuk kembali ke halaman login
        btnExit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()  // Mengakhiri MenuActivity setelah kembali ke login
        }
    }
}