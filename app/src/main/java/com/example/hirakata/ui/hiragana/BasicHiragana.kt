// File activity yang digunakan untuk menampilkan daftar huruf dari sesi 'Basic Hiragana'

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

class BasicHiragana : AppCompatActivity() {

      // Deklarasi Variabel Untuk Recyle View
      private lateinit var rvHiragana: RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
               super.onCreate(savedInstanceState)
               setContentView(R.layout.activity_layout_item)

               val backBtn : ImageButton = findViewById(R.id.btnBack)
               val infoBtn : ImageButton = findViewById(R.id.btnInfo)
               val sessionTitle : TextView = findViewById(R.id.sessionTitle)

               // Header Title, Back Button, Info Button Section
               sessionTitle.text = "Basic Hiragana"

               backBtn.setOnClickListener {
                   finish()
               }

               infoBtn.setOnClickListener {
                   InfoDialog.show(
                       this,
                       "Apa Itu Hiragana?",
                       "Hiragana Adalah Salah Satu Dari Tiga Sistem Penulisan Utama Dalam Bahasa Jepang. Umumnya Digunakan Untuk Menulis Kata-kata Asli Jepang, Partikel, Dan Akhiran Kata Kerja \n\nPada Huruf Dibawah Kamu Bisa Menekan Semua Huruf Yang Ada Untuk Melihat Detail Dari Setiap Hurufnya. \n\nTahan Huruf Yang Di Pilih Untuk Menandai dan Membatalkan Tanda Jika Sudah Menandai Nya."
                   )
               }

               // List Character Hiragana Section
               rvHiragana = findViewById(R.id.rvHiragana)
               rvHiragana.layoutManager = GridLayoutManager(this, 3)

               val hiraganaList = JsonLoader.loadHiragana(this, "basic_hiragana.json")

               val adapter = HiraganaAdapter(hiraganaList)
               rvHiragana.adapter = adapter

      }

}