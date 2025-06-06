package com.example.mathlern.data.models

data class Question(
    val questionText: String,
    val choices: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String = ""
)

data class Answered(
    val questionIndex: Int,
    val selectedAnswerIndex: Int,
    val isCorrect: Boolean
)