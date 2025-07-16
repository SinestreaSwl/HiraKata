package com.example.hirakata.ui.hiragana

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

class SokuonHiragana : AppCompatActivity() {

      private lateinit var rvHiragana: RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_layout_item)

          val backBtn : ImageButton = findViewById(R.id.btnBack)
          val infoBtn : ImageButton = findViewById(R.id.btnInfo)
          val sessionTitle : TextView = findViewById(R.id.sessionTitle)

          // Header Title, Back Button, Info Button Section
          sessionTitle.text = "Sokuon Di Hiragana"

          backBtn.setOnClickListener {
              finish()
          }

          infoBtn.setOnClickListener {
             InfoDialog.show(
                 this,
                 "Apa Itu Sokuon?",
                 "Sokuon adalah karakter kecil 「っ」yang digunakan untuk menunjukkan bunyi konsonan ganda atau hentakan pendek dalam pengucapan. Contohnya, pada kata「がっこう」(gakkou) yang berarti \"sekolah\", bunyi 'k' diulang dan diberi penekanan pendek.\n\nSokuon biasanya muncul sebelum konsonan seperti k, s, t, dan p. Penggunaannya penting karena mengubah arti kata, misalnya:\n さか (saka) = lereng | さっか (sakka) = penulis\n\nPada Huruf Dibawah Kamu Bisa Menekan Semua Huruf Yang Ada Untuk Melihat Detail Dari Setiap Hurufnya.\n\nPerhatikan bahwa 「っ」tidak memiliki suara sendiri, tapi hanya memperpendek bunyi berikutnya. \n\nTahan Huruf Yang Di Pilih Untuk Menandai dan Membatalkan Tanda Jika Sudah Menandai Nya."
             )
        }

          // List Character Hiragana Section
          rvHiragana = findViewById(R.id.rvHiragana)
          rvHiragana.layoutManager = GridLayoutManager(this, 3)

          val hiraganaList = JsonLoader.loadHiragana(this, "sokuon_hiragana.json")

          val adapter = HiraganaAdapter(hiraganaList)
          rvHiragana.adapter = adapter

      }
}