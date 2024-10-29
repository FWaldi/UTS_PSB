package com.example.gamewarna2

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var txtWarna: TextView
    lateinit var txtScore: TextView
    lateinit var txtTimer: TextView
    lateinit var gridLayout: GridLayout
    var warnaArray = arrayOf("Red", "Green", "Blue")
    var correctColor = ""
    var score = 0

    // Timer untuk menghitung waktu 10 detik
    lateinit var countDownTimer: CountDownTimer
    var timeLeftInMillis: Long = 10000 // 10 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        txtWarna = findViewById(R.id.txtWarna)
        txtScore = findViewById(R.id.txtScore)
        txtTimer = findViewById(R.id.txtTimer)
        gridLayout = findViewById(R.id.gridLayout)

        setRandomColor()
        startTimer()

        // Klik kotak warna
        gridLayout.getChildAt(0).setOnClickListener { checkAnswer("Red") }
        gridLayout.getChildAt(1).setOnClickListener { checkAnswer("Green") }
        gridLayout.getChildAt(2).setOnClickListener { checkAnswer("Blue") }
    }

    private fun setRandomColor() {
        correctColor = warnaArray[Random.nextInt(warnaArray.size)]
        txtWarna.text = correctColor
    }

    private fun checkAnswer(selectedColor: String) {
        if (selectedColor == correctColor) {
            score += 10  // Tambahkan 10 poin untuk jawaban yang benar
            txtScore.text = "Score: $score"
            setRandomColor()
            resetTimer()  // Reset timer setelah pilihan yang benar
        } else {
            gameOver()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                txtTimer.text = "Time: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                gameOver()  // Jika waktu habis, game over
            }
        }.start()
    }

    private fun resetTimer() {
        countDownTimer.cancel()  // Batalkan timer saat ini
        timeLeftInMillis = 10000  // Reset waktu ke 10 detik
        startTimer()  // Mulai ulang timer
    }

    private fun gameOver() {
        countDownTimer.cancel()
        Toast.makeText(this, "Game Over!", Toast.LENGTH_LONG).show()

        // Simpan skor ke SharedPreferences
        val sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val previousScore = sharedPreferences.getInt("score", 0)
        editor.putInt("score", score.coerceAtLeast(previousScore))  // Simpan skor tertinggi
        editor.apply()

        // Kembali ke MenuActivity
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}
