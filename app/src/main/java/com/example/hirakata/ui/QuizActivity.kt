package com.example.hirakata.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.hirakata.R
import com.example.hirakata.model.QuizQuestion
import com.example.hirakata.util.ProgressManager

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnNext: Button

    private val questions = listOf(
        QuizQuestion("Mana Huruf Untuk 'A' Dalam Hiragana ?", listOf("あ", "い", "う", "え"), 0),
        QuizQuestion("Mana Huruf Untuk 'KA' Dalam Hiragana ?", listOf("さ", "か", "た", "な"), 1),
        QuizQuestion("Dibaca Apa Huruf Hiragana Ini 'く' ?", listOf("ku", "ki", "ko", "ke"), 0)
    )

    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnNext.setOnClickListener {

            val checkedId = rgOptions.checkedRadioButtonId

            if (checkedId != -1) {
                val selectedIndex = rgOptions.indexOfChild(findViewById(checkedId))
                if (selectedIndex == questions[currentIndex].correctAnswerIndex) {
                    score++
                }

                currentIndex++
                if (currentIndex < questions.size) {
                    loadQuestion()
                } else {
                    if (score == questions.size) {
                        Toast.makeText(this, "Quiz Selesai! Sesi Baru Terbuka!!", Toast.LENGTH_SHORT).show()
                        ProgressManager.unlock(this, "dakuten")
                    } else {
                        Toast.makeText(this, "Nilai Kamu Tidak Mencukupi, Coba Lagi Ya!", Toast.LENGTH_SHORT).show()
                    }
                    finish()
                }

            } else {
                Toast.makeText(this, "Pilih Jawaban Nya Terlebih Dahulu Ya!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun loadQuestion() {

            val question = questions[currentIndex]

            tvQuestion.text = question.question
            rgOptions.removeAllViews()
            question.options.forEach {
                     val rb = RadioButton(this)
                     rb.text = it
                     rgOptions.addView(rb)
            }
    }

}