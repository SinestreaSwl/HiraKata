// File untuk model data apa saja yang digunakan pada tiap huruf yang ada didalam aplikasi
package com.example.hirakata.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocabulary (
      val hiragana: String,
      val romaji: String,
      val means: String
) : Parcelable

@Parcelize
class HiraganaItem (
      val hiragana: String,
      val romaji: String,
      val vocabList: List<Vocabulary>,
) : Parcelable