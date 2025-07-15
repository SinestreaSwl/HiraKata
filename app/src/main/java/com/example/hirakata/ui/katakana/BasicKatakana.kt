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

class BasicKatakana : AppCompatActivity()  {

      // Deklarasi Variabel Untuk Recyle View
      private lateinit var rvHiragana: RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_layout_item)

          // Deklarasi Untuk Variabel Button Back, Info, Dan Title Sesi
          val backBtn : ImageButton = findViewById(R.id.btnBack)
          val infoBtn : ImageButton = findViewById(R.id.btnInfo)
          val sessionTitle : TextView = findViewById(R.id.sessionTitle)

          // Header Title, Back Button, Info Button Section
          sessionTitle.text = "Basic Katakana"

          backBtn.setOnClickListener {
              finish()
          }

          infoBtn.setOnClickListener {
              InfoDialog.show(
                  this,
                  "Apa Itu Katakana?",
                  "Katakana Adalah Salah Satu Dari Tiga Sistem Penulisan Utama Dalam Bahasa Jepang. Umumnya Digunakan Untuk Menulis Kata-kata Serapan, Nama-Nama Asing, Juga Istilah Istilah Asing\n\nPada Huruf Dibawah Kamu Bisa Menekan Semua Huruf Yang Ada Untuk Melihat Detail Dari Setiap Hurufnya. \n\nTahan Huruf Yang Di Pilih Untuk Menandai dan Membatalkan Tanda Jika Sudah Menandai Nya."
              )
          }

          // List Character Hiragana Section
          rvHiragana = findViewById(R.id.rvHiragana)
          rvHiragana.layoutManager = GridLayoutManager(this, 3)

          val hiraganaList = JsonLoader.loadHiragana(this, "basic_katakana.json")

          val adapter = HiraganaAdapter(hiraganaList)
          rvHiragana.adapter = adapter

      }

}