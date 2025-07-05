// File activity yang digunakan untuk menampilkan daftar sesi huruf hiragana dan mendirect ke sesi yang dipilih

package com.example.hirakata.ui.hiragana

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.model.HiraganaSection
import com.example.hirakata.quiz.QuizActivity
import com.example.hirakata.util.ProgressManager

class HiraganaMenuActivity : AppCompatActivity() {


      private lateinit var rvMenu: RecyclerView
      private lateinit var sections: List<HiraganaSection>

      override fun onCreate(savedInstanceState: Bundle?) {
               super.onCreate(savedInstanceState)
               setContentView(R.layout.activity_hiragana_menu)

               rvMenu = findViewById(R.id.rvHiraganaMenu)
               rvMenu.layoutManager = LinearLayoutManager(this)

               loadSections()
      }

      private fun loadSections() {
              sections = listOf(
                  HiraganaSection(
                      "Basic Hiragana",
                      "basic",
                      ProgressManager.isUnlocked(this, "basic")
                  ),
                  HiraganaSection(
                      "Dakuten & Handakuten Di Hiragana",
                      "dakuten",
                      ProgressManager.isUnlocked(this, "dakuten")
                  ),
                  HiraganaSection(
                      "Yōon Di Hiragana",
                      "yoon",
                      ProgressManager.isUnlocked(this, "yoon")
                  ),
                  HiraganaSection(
                      "Sokuon Di Hiragana",
                      "sokuon",
                      ProgressManager.isUnlocked(this, "sokuon")
                  )
              )

               val adapter = HiraganaSectionAdapter(this, sections) { section ->
                   val intent = when (section.key) {
                       "basic" -> Intent(this, BasicHiragana::class.java)
                       "dakuten", "yoon" -> Intent(this, QuizActivity::class.java).apply {
                           putExtra("QUIZ_KEY", section.key)
                       }

                       else -> null
                   }
                   intent?.let { startActivity(it) }
               }
               rvMenu.adapter = adapter
      }

      override fun onResume() {
               super.onResume()
               loadSections()
    }

}