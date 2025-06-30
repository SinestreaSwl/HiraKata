// File untuk model data yang digunakan untuk quiz
package com.example.hirakata.model

data class QuizQuestion (
     val question: String,
     val options: List<String>,
     val correctAnswer: Int
)