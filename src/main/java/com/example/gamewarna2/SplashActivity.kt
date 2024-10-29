package com.example.gamewarna2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // 3 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Menunda perpindahan ke MainActivity setelah 3 detik
        Handler(Looper.getMainLooper()).postDelayed({
            // Berpindah ke MainActivity (halaman login)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Menutup SplashActivity agar tidak bisa kembali ke splash screen
        }, splashTimeOut)
    }
}
