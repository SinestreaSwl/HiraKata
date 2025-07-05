// File activity main menu, yang menampilkan 3 sesi menu utama dalam aplikasi
package com.example.hirakata

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.hirakata.ui.hiragana.HiraganaMenuActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Button Event For Direct To Hiragana Section
        val hiraganaBtn : Button = findViewById(R.id.hiraganaBtn)
        hiraganaBtn.setOnClickListener {
            val intent = Intent(this, HiraganaMenuActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_in)
        }


        val logo = findViewById<ImageView>(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        logo.startAnimation(animation)

        // For Next Section Btw...

    }
}