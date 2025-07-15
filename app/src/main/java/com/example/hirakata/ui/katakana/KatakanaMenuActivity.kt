package com.example.hirakata.ui.katakana

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.model.HiraganaSection
import com.example.hirakata.util.ProgressManager
import com.example.hirakata.ui.hiragana.HiraganaSectionAdapter
import com.example.hirakata.ui.katakana.BasicKatakana

class KatakanaMenuActivity : AppCompatActivity() {

    // Deklarasi Variable Untuk Fitur RecyleView
    private lateinit var rvMenu: RecyclerView
    private lateinit var sections: List<HiraganaSection>

    // Block Code Untuk Menerapkan RecycleView | Back Button | Info Button | Title Sesi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiragana_menu)

        // Deklarasi Variabel Untuk Button Back, Info, Title Sesi, dan Recyle Menu
        val backBtn: ImageButton = findViewById(R.id.btnBack)
        val infoBtn: ImageButton = findViewById(R.id.btnInfo)
        val sessionTitle: TextView = findViewById(R.id.sessionTitle)
        rvMenu = findViewById(R.id.rvHiraganaMenu)
        rvMenu.layoutManager = LinearLayoutManager(this)

        // Title Sesi
        sessionTitle.text = "Katakana"

        // Button Back Ke Menu Utama
        backBtn.setOnClickListener {
            finish()
        }

        // Button Info Cara Membuka Sesi Yang Locked
        infoBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Cara Membuka Sesi Yang Locked")
                .setMessage(
                    """
                            - Sesi Basic Secara Otomatis Akan Terbuka
                            - Sesi Dakuten & Handakuten Akan Terbuka Setelah Kamu Menjawab Quiz Yang Tersedia
                            - Sesi Yoon Akan Terbuka Setelah Kamu Menjawab Quiz Yang Tersedia
                            - Sesi Sokuon Akan Terbuka Setelah Kamu Menjawab Quiz Yang Tersedia
                            
                            Terdapat 10 Soal Quiz Untuk Tiap Sesi
                            Skor Yang Harus Didapatkan Untuk Membuka Sesi Terbaru Adalah 10/10
                            Kamu Dapat Mengulang Terus Menerus Quiznya Apabila Memang Skor Yang Dibutuhkan Belum Mencukupi
                        """.trimIndent()
                )
                .setPositiveButton("Wakarimashita") { dialog, _ -> dialog.dismiss() }
                .show()
        }
        loadSections()
    }

    private fun loadSections() {
        sections = listOf(
            HiraganaSection(
                "Basic Katakana",
                "katakana_basic",
                ProgressManager.isUnlocked(this, "katakana_basic")
            ),
            HiraganaSection(
                "Dakuten & Handakuten Di Katakana",
                "dakuten_katakana",
                ProgressManager.isUnlocked(this, "dakuten_katakana")
            ),

            HiraganaSection(
                "Yōon Di Katakana",
                "yoon_katakana",
                ProgressManager.isUnlocked(this, "yoon_katakana")
            ),

            HiraganaSection(
                "Sokuon Di Katakana",
                "sokuon_katakana",
                ProgressManager.isUnlocked(this, "sokuon_katakana")
            )
        )

        val adapter = KatakanaSectionAdapter(this, sections) { section ->
            val intent = when (section.key) {
                "katakana_basic" -> Intent(this, BasicKatakana::class.java)
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