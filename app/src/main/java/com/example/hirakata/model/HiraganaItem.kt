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
      var isMarked: Boolean = false
) : Parcelable