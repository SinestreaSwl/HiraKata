// File adapter untuk menampilkan daftar sesi yang ada di main menu 'hiragana'

package com.example.hirakata.ui.hiragana

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.model.HiraganaSection
import com.example.hirakata.quiz.QuizActivity

class HiraganaSectionAdapter (
    private val context: Context,
    private val sections: List<HiraganaSection>,
    private val onItemClick: (HiraganaSection) -> Unit
) : RecyclerView.Adapter<HiraganaSectionAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
          val tvSectionTitle = view.findViewById<TextView>(R.id.tvSectionTitle)
          val tvStatus = view.findViewById<TextView>(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
             val view = LayoutInflater.from(context).inflate(R.layout.item_hiragana_section, parent, false)
             return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
             val section = sections[position]
             holder.tvSectionTitle.text = section.title
             holder.tvStatus.text = if (section.isUnlocked) "Unlocked" else "Locked"

             holder.itemView.setOnClickListener {
                    if (section.isUnlocked) {
                        onItemClick(section)
                    } else {
                        AlertDialog.Builder(context)
                             .setTitle("Sesi Terkunci")
                             .setMessage("Untuk Membuka Sesi '${section.title}', Kamu Harus Menyelesaikan Materi Quiz Dari Sesi Sebelumnya Terlebih Dahulu")
                             .setPositiveButton("Ikuzo!") { _, _ ->
                                 onItemClick(section)
                             }
                            .setNegativeButton("Nanti Dulu", null)
                            .show()
                    }
             }
    }

    override fun getItemCount() = sections.size

}