package com.example.hirakata.ui.katakana

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.adapter.HiraganaAdapter
import com.example.hirakata.util.InfoDialog
import com.example.hirakata.util.JsonLoader

class YoonKatakana : AppCompatActivity()  {

      private lateinit var rvHiragana: RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_layout_item)

          val backBtn : ImageButton = findViewById(R.id.btnBack)
          val infoBtn : ImageButton = findViewById(R.id.btnInfo)
          val sessionTitle : TextView = findViewById(R.id.sessionTitle)

          // Header Title, Back Button, Info Button Section
          sessionTitle.text = "Yoon Di Katakana"

          backBtn.setOnClickListener {
              finish()
          }

          infoBtn.setOnClickListener {
              InfoDialog.show(
                  this,
                  "Apa Itu Yoon?",
                  "Konsepnya Sama Seperti Yang Ada Di Hiragana\n\nYang Mana Yōon adalah kombinasi dari konsonan + vokal kecil seperti 「ゃ」「ゅ」「ょ」untuk membentuk bunyi majemuk, contohnya : き + ゃ = きゃ (kya)\n\nDan Bunyi ini tidak bisa dipecah-pecah jadi dua suku kata, karena terbaca sebagai satu unit.\n\nPada Huruf Dibawah Kamu Bisa Menekan Semua Huruf Yang Ada Untuk Melihat Detail Dari Setiap Hurufnya. \n\nTahan Huruf Yang Di Pilih Untuk Menandai dan Membatalkan Tanda Jika Sudah Menandai Nya."
              )
          }

          // List Character Hiragana Section
          rvHiragana = findViewById(R.id.rvHiragana)
          rvHiragana.layoutManager = GridLayoutManager(this, 3)

          val hiraganaList = JsonLoader.loadHiragana(this, "yoon_katakana.json")

          val adapter = HiraganaAdapter(hiraganaList)
          rvHiragana.adapter = adapter

    }
}