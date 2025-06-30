// Representasi data untuk 1 sesi huruf hiragana, seperti judul sesi nya, status sesinya
// apakah sesi tersebut terbuka atau tidak
package com.example.hirakata.model

class HiraganaSection (
    val title : String,
    val key : String,
    val isUnlocked : Boolean
    )