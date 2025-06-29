package com.example.hirakata.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.hirakata.R
import com.example.hirakata.model.QuizQuestion
import com.example.hirakata.util.ProgressManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import java.io.Reader

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnNext: Button

    private lateinit var questions: List<QuizQuestion>
    private var currentIndex = 0
    private var score = 0
    private val totalQuestions = 10

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        btnNext = findViewById(R.id.btnNext)

        loadQuestionsFromJson()

        btnNext.setOnClickListener {

            val checkedId = rgOptions.checkedRadioButtonId

            if (checkedId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                val selectedIndex = selectedRadioButton.tag as Int

                if (selectedIndex == questions[currentIndex].correctAnswer) {
                    score++
                }

                currentIndex++
                if (currentIndex < totalQuestions) {
                    loadQuestion()
                } else {
                    showQuizResult()
                }
            } else {
                Toast.makeText(this, "Pilih Jawaban Nya Terlebih Dahulu Ya!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun loadQuestionsFromJson() {
        val inputStream = resources.openRawResource(R.raw.basic_hiragana_quiz)
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<QuizQuestion>>() {}.type
        val allQuestions : List<QuizQuestion> = Gson().fromJson(reader, type)

        questions = allQuestions.shuffled().take(totalQuestions)
        loadQuestion()
    }

    private fun loadQuestion() {

            val question = questions[currentIndex]

            tvQuestion.text = question.question
            rgOptions.removeAllViews()

            question.options.forEachIndexed { index, option ->
                     val rb = RadioButton(this)
                     rb.tag = index
                     rb.text = option
                     rb.textSize = 16f
                     rb.setPadding(8, 16, 8, 16)
                     rgOptions.addView(rb)
            }
    }

    private fun showQuizResult() {
            val builder = android.app.AlertDialog.Builder(this)
            val message = if (score == totalQuestions) {
                ProgressManager.unlock(this, "dakuten")
                "Omedetou! Kamu Menjawab Semua Pertanyaan Dengan Benar! \n\nSkor: $score/$totalQuestions\nSesi Baru Telah Terbuka!"
            } else {
                "Skor Kamu: $score/$totalQuestions\n Coba Lagi Untuk Membuka Sesi Berikutnya Ya!"
            }

            builder.setTitle("Hasil Quiz")
                   .setMessage(message)
                   .setPositiveButton("Oke") { dialog, _ ->
                       dialog.dismiss()
                       finish()
                   }
                   .setCancelable(false)
                   .show()
    }

}