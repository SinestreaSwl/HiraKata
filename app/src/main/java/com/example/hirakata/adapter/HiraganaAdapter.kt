// File Adapter Untuk 'RecyclerView' yang berguna untuk
// menampilkan daftar-daftar huruf hiragana

package com.example.hirakata.adapter

import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hirakata.R
import com.example.hirakata.model.HiraganaItem
import com.example.hirakata.ui.hiragana.HiraganaDetailDialog
import com.example.hirakata.util.ProgressManager

class HiraganaAdapter (
      private val hiraganaList: List<HiraganaItem>
) : RecyclerView.Adapter<HiraganaAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
          val hiragana = view.findViewById<TextView>(R.id.tvHiragana)
          val romaji = view.findViewById<TextView>(R.id.tvRomaji)
          val vocab = view.findViewById<TextView>(R.id.tvVocab)
          val card = view.findViewById<LinearLayout>(R.id.hiraganaCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
             val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hiragana, parent, false)
             return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

             val item = hiraganaList[position]
             val ctx = holder.itemView.context
             val key = item.hiragana

             holder.hiragana.text = item.hiragana
             holder.romaji.text = item.romaji
             holder.vocab.text = item.vocabList.joinToString("\n") {
                 "${it.hiragana} - ${it.romaji} (${it.means})"
             }

            val isMarked = ProgressManager.isMarked(ctx, key)



            // Marked Item Background Session
            val backgroundRes = if (isMarked)
                R.drawable.bg_hiragana_item_marked
            else
                R.drawable.bg_hiragana_item
            holder.card.setBackgroundResource(backgroundRes)

            // Pop-Up Item Section
             holder.itemView.setOnClickListener {
                 val fragmentManager = (ctx as AppCompatActivity).supportFragmentManager
                 HiraganaDetailDialog(item).show(fragmentManager, "HiraganaDetail")
             }

            // Marked Item - Long Press To Marked / Unmarked
            holder.itemView.setOnLongClickListener {
                 if (isMarked) ProgressManager.unmark(ctx, key)
                 else ProgressManager.mark(ctx, key)
                 notifyItemChanged(position)
                 true
            }

    }

    override fun getItemCount() = hiraganaList.size

}