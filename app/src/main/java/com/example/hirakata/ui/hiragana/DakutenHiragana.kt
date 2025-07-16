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

class DakutenHiragana : AppCompatActivity() {

      private lateinit var rvHiragana : RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_layout_item)

          val backBtn : ImageButton = findViewById(R.id.btnBack)
          val infoBtn : ImageButton = findViewById(R.id.btnInfo)
          val sessionTitle : TextView = findViewById(R.id.sessionTitle)

          // Header Title, Back Button, Info Button Section
          sessionTitle.text = "Dakuten & Handakuten Di Hiragana"

          backBtn.setOnClickListener {
              finish()
          }

          infoBtn.setOnClickListener {
              InfoDialog.show(
                  this,
                  "Apa Itu Dakuten Dan Handakuten",
                  "Dakuten (゛) dan Handakuten (゜) adalah tanda di atas huruf hiragana yang mengubah suara huruf tersebut.\n\nSeperti か (ka) menjadi が (ga) \nDari は (ha) menjadi ば (ba) atau ぱ (pa)\n\nItulah kenapa fungsinya penting dalam pelafalan kata-kata bahasa Jepang\nKamu bisa menekan setiap huruf untuk melihat detailnya!\n\nTahan Huruf Yang Di Pilih Untuk Menandai dan Membatalkan Tanda Jika Sudah Menandai Nya."
              )
          }

          // List Character Hiragana Section
          rvHiragana = findViewById(R.id.rvHiragana)
          rvHiragana.layoutManager = GridLayoutManager(this, 3)

          val hiraganaList = JsonLoader.loadHiragana(this, "dakuten_handakuten_hiragana.json")

          val adapter = HiraganaAdapter(hiraganaList)
          rvHiragana.adapter = adapter
      }
}