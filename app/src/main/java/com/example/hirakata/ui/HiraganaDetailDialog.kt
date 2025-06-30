// File class untuk menampilkan detail huruf hiragana yang dipilih
package com.example.hirakata.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.hirakata.R
import com.example.hirakata.model.HiraganaItem


class HiraganaDetailDialog (private val item: HiraganaItem) : DialogFragment() {
      override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {

               val dialog = Dialog(requireContext())
               val view = LayoutInflater.from(context).inflate(R.layout.dialog_hiragana_detail, null)

               val tvBigHiragana = view.findViewById<TextView>(R.id.tvBigHiragana)
               val tvDetailRomaji = view.findViewById<TextView>(R.id.tvDetailRomaji)
               val tvDetailVocab = view.findViewById<TextView>(R.id.tvDetailVocab)

               tvBigHiragana.text = item.hiragana
               tvDetailRomaji.text = item.romaji
               tvDetailVocab.text = item.vocabList.joinToString("\n") {
                       "${it.hiragana} - ${it.romaji} (${it.means})"
               }

               dialog.setContentView(view)
               dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
               return dialog

      }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomDialogStyle)
    }
}