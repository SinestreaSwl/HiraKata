// File activity yang digunakan untuk menampilkan daftar huruf dari sesi 'Basic Hiragana'

package com.example.hirakata.ui.hiragana

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.adapter.HiraganaAdapter
import com.example.hirakata.util.JsonLoader

class BasicHiragana : AppCompatActivity() {

      private lateinit var rvHiragana: RecyclerView

      override fun onCreate(savedInstanceState: Bundle?) {
               super.onCreate(savedInstanceState)
               setContentView(R.layout.activity_basic_hiragana)

               val backBtn : ImageButton = findViewById(R.id.btnBack)
               val infoBtn : ImageButton = findViewById(R.id.btnInfo)
               val sessionTitle : TextView = findViewById(R.id.sessionTitle)

               // Header Title, Back Button, Info Button Section
               sessionTitle.text = "Basic Hiragana"

               backBtn.setOnClickListener {
                   finish()
               }

               infoBtn.setOnClickListener {
                   val dialogView = layoutInflater.inflate(R.layout.dialog_info_hiragana, null)
                   val dialog = AlertDialog.Builder(this)
                       .setView(dialogView)
                       .create()

                   dialogView.findViewById<Button>(R.id.btnCloseInfo).setOnClickListener {
                       dialog.dismiss()
                   }

                   dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                   dialog.show()

               }



               // List Character Hiragana Section
               rvHiragana = findViewById(R.id.rvHiragana)
               rvHiragana.layoutManager = GridLayoutManager(this, 3)

               val hiraganaList = JsonLoader.loadHiragana(this)

               val adapter = HiraganaAdapter(hiraganaList)
               rvHiragana.adapter = adapter

      }

}