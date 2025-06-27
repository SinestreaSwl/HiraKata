package com.example.hirakata.model

data class QuizQuestion (
     val question: String,
     val options: List<String>,
     val correctAnswerIndex: Int
)