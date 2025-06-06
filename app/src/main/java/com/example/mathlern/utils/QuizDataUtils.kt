package com.example.mathlern.utils

import com.example.mathlern.data.models.Question

// Function to create dummy questions for testing
fun getDummyMathQuestions(
    topicTitle: String,
    quizType: String,
    level: String):
        List<Question> {
    return when {
        topicTitle.contains("Penjumlahan", ignoreCase = true) -> {
            listOf(
                Question(
                    questionText = "9 + 10",
                    choices = listOf("10", "19", "12", "21"),
                    correctAnswerIndex = 1,
                    explanation = "\t  9\n \t10\n------\n\t19"
                ),
                Question(
                    questionText = "15 + 26",
                    choices = listOf("31", "41", "51", "61"),
                    correctAnswerIndex = 1,
                    explanation = "\t15\n \t26\n------\n\t41"
                ),
                Question(
                    questionText = "8 + 9",
                    choices = listOf("15", "16", "17", "18"),
                    correctAnswerIndex = 2,
                    explanation = "\t8\n \t9\n------\n\t17"
                ),
                Question(
                    questionText = "10 + 20",
                    choices = listOf("30", "50", "20", "120"),
                    correctAnswerIndex = 0,
                    explanation = "\t10\n \t20\n------\n\t30"
                )
            )
        }
        topicTitle.contains("Pengurangan", ignoreCase = true) -> {
            listOf(
                Question(
                    questionText = "15 - 7",
                    choices = listOf("5", "7", "8", "10"),
                    correctAnswerIndex = 2
                ),
                Question(
                    questionText = "42 - 27",
                    choices = listOf("15", "16", "17", "18"),
                    correctAnswerIndex = 0
                ),
                Question(
                    questionText = "25 - 8",
                    choices = listOf("15", "16", "17", "18"),
                    correctAnswerIndex = 2
                )
            )
        }
        else -> {
            listOf(
                Question(
                    questionText = "Contoh soal matematika 1",
                    choices = listOf("Pilihan A", "Pilihan B", "Pilihan C", "Pilihan D"),
                    correctAnswerIndex = 1
                ),
                Question(
                    questionText = "Contoh soal matematika 2",
                    choices = listOf("Pilihan A", "Pilihan B", "Pilihan C", "Pilihan D"),
                    correctAnswerIndex = 0
                )
            )
        }
    }
}