// File activity yang digunakan untuk menampilkan daftar sesi huruf hiragana dan mendirect ke sesi yang dipilih

package com.example.hirakata.ui.hiragana

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.model.HiraganaSection
import com.example.hirakata.quiz.QuizActivity
import com.example.hirakata.util.ProgressManager

class HiraganaMenuActivity : AppCompatActivity() {

      // Deklarasi Variable Untuk Fitur RecyleView
      private lateinit var rvMenu: RecyclerView
      private lateinit var sections: List<HiraganaSection>

      // Block Code Untuk Menerapkan RecycleView | Back Button | Info Button | Title Sesi
      override fun onCreate(savedInstanceState: Bundle?) {
               super.onCreate(savedInstanceState)
               setContentView(R.layout.activity_hiragana_menu)

               // Deklarasi Variabel Untuk Button Back, Info, Title Sesi, dan Recyle Menu
               val backBtn : ImageButton = findViewById(R.id.btnBack)
               val infoBtn : ImageButton = findViewById(R.id.btnInfo)
               val sessionTitle : TextView = findViewById(R.id.sessionTitle)
               rvMenu = findViewById(R.id.rvHiraganaMenu)
               rvMenu.layoutManager = LinearLayoutManager(this)

               // Title Sesi
               sessionTitle.text = "Hiragana"

               // Button Back Ke Menu Utama
               backBtn.setOnClickListener {
                   finish()
               }

               // Button Info Cara Membuka Sesi Yang Locked
               infoBtn.setOnClickListener {
                   AlertDialog.Builder(this)
                        .setTitle("Cara Membuka Sesi Yang Locked")
                        .setMessage("""
                            - Sesi Basic Secara Otomatis Akan Terbuka
                            - Sesi Dakuten & Handakuten Akan Terbuka Setelah Kamu Menjawab Quiz Yang Tersedia
                            - Sesi Yoon Akan Terbuka Setelah Kamu Menjawab Quiz Yang Tersedia
                            - Sesi Sokuon Akan Terbuka Setelah Kamu Menjawab Quiz Yang Tersedia
                            
                            Terdapat 10 Soal Quiz Untuk Tiap Sesi
                            Skor Yang Harus Didapatkan Untuk Membuka Sesi Terbaru Adalah 10/10
                            Kamu Dapat Mengulang Terus Menerus Quiznya Apabila Memang Skor Yang Dibutuhkan Belum Mencukupi
                        """.trimIndent())
                       .setPositiveButton("Wakarimashita") { dialog, _ -> dialog.dismiss()}
                       .show()
               }

               loadSections()

      }

      // Block Code Untuk Memuat Sesi Sesi Yang Ada
      private fun loadSections() {
              sections = listOf(

                  HiraganaSection(
                      "Basic Hiragana",
                      "hiragana_basic",
                      ProgressManager.isUnlocked(this, "hiragana_basic")
                  ),

                  HiraganaSection(
                      "Dakuten & Handakuten Di Hiragana",
                      "hiragana_dakuten",
                      ProgressManager.isUnlocked(this, "hiragana_dakuten")
                  ),

                  HiraganaSection(
                      "Yōon Di Hiragana",
                      "hiragana_yoon",
                      ProgressManager.isUnlocked(this, "hiragana_yoon")
                  ),

                  HiraganaSection(
                      "Sokuon Di Hiragana",
                      "hiragana_sokuon",
                      ProgressManager.isUnlocked(this, "hiragana_sokuon")
                  )

              )

               val adapter = HiraganaSectionAdapter(this, sections) { section ->
                   val intent = when (section.key) {

                       "hiragana_basic" -> Intent(this, BasicHiragana::class.java)

                       "hiragana_dakuten" -> {
                           if (!section.isUnlocked) {
                               val quizIntent = Intent(this, QuizActivity::class.java).apply {
                                   putExtra("QUIZ_KEY", "basic")
                               }
                               quizIntent
                           } else {
                               Intent(this, DakutenHiragana::class.java)
                           }
                       }

                       "hiragana_yoon" -> {
                           if (!section.isUnlocked) {
                               val quizIntent = Intent(this, QuizActivity::class.java).apply {
                                   putExtra("QUIZ_KEY", "dakuten")
                               }
                               quizIntent
                           } else {
                               Intent(this, YoonHiragana::class.java)
                           }
                       }

                       "hiragana_sokuon" -> {
                           if (!section.isUnlocked) {
                               val quizIntent = Intent(this, QuizActivity::class.java).apply {
                                   putExtra("QUIZ_KEY", "yoon")
                               }
                               quizIntent
                           } else {
                               Intent(this, SokuonHiragana::class.java)
                           }
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