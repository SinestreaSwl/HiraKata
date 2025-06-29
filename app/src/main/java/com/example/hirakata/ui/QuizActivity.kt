package com.example.hirakata.ui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.hirakata.R
import com.example.hirakata.model.QuizQuestion
import com.example.hirakata.util.ProgressManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestionNumber: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var glOptions: GridLayout
    private lateinit var btnNext: Button

    private lateinit var questions: List<QuizQuestion>
    private var currentIndex = 0
    private var score = 0
    private val totalQuestions = 10

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        glOptions = findViewById(R.id.glOptions)
        btnNext = findViewById(R.id.btnNext)

        loadQuestionsFromJson()

        btnNext.setOnClickListener {

            val selectedIndex = glOptions.tag as? Int

            if (selectedIndex != null) {

                if (selectedIndex == questions[currentIndex].correctAnswer) {
                    score++
                }

                currentIndex++
                glOptions.tag = null
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

            tvQuestionNumber.text = "Soal ${currentIndex + 1} / $totalQuestions"
            tvQuestion.text = question.question

            glOptions.removeAllViews()
            glOptions.tag = null

            question.options.forEachIndexed { index, option ->
                     val card = CardView(this).apply {
                         radius = 20f
                         cardElevation = 6f
                         useCompatPadding = true
                         setCardBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.white))
                         val params = GridLayout.LayoutParams().apply {
                             width = 0
                             height = GridLayout.LayoutParams.WRAP_CONTENT
                             columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                             rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                             setMargins(16, 16, 16, 16)
                         }
                         layoutParams = params
                     }

                     val textView = TextView(this).apply {
                         text = option
                         textSize = 18f
                         setPadding(32, 32, 32, 32)
                         setTextColor(ContextCompat.getColor(this@QuizActivity, R.color.bulaku))
                         textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                         tag = index
                         setOnClickListener {
                             glOptions.children.forEach { view ->
                                 if (view is CardView) {
                                     view.setCardBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.white))
                                 }
                             }
                             card.setCardBackgroundColor(ContextCompat.getColor(this@QuizActivity, R.color.teal_200))
                             glOptions.tag = index
                         }
                     }

                     card.addView(textView)
                     glOptions.addView(card)
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