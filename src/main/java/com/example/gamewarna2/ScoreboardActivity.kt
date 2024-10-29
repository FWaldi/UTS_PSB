package com.example.gamewarna2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ScoreboardActivity : AppCompatActivity() {

    lateinit var lvScores: ListView
    lateinit var scoresList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)

        lvScores = findViewById(R.id.lvScores)

        // Ambil skor dari SharedPreferences
        val sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val score = sharedPreferences.getInt("score", 0)

        // Daftar dummy pemain dan skor (Anda bisa menambahkan logika lebih lanjut untuk menyimpan banyak skor)
        scoresList = mutableListOf(
            "Pemain A - $score",
            "Pemain B - 70",
            "Pemain C - 50"
        )

        // Urutkan berdasarkan skor tertinggi
        scoresList.sortByDescending {
            it.split("-")[1].trim().toInt()
        }

        // Set adapter untuk menampilkan skor
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, scoresList)
        lvScores.adapter = adapter
    }
}
