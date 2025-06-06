package com.example.mathlern.ui.screens

import com.example.mathlern.data.models.Question
import com.example.mathlern.data.models.Answered

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathlern.ui.theme.bodyFontFamily

@Composable
fun LatihanQuizScreen(
    topicTitle: String,
    quizType: String,
    level: String,
    subIcon: Int,
    onBack: () -> Unit,
    onFinish: (score: Int) -> Unit,
    question: List<Question>
){
    var currentIndex by remember {mutableStateOf(0)}
    var selectedAnswer by remember {mutableStateOf<Int?>(null)}
    var showConfirmDialog by remember {mutableStateOf(false)}
    var isReviewMode by remember {mutableStateOf(false)}
    val currentQuestion = question[currentIndex]
    val totalQuestions = question.size

    val answers = remember {mutableStateListOf<Answered?>().apply {
        repeat(totalQuestions) {
            add(null)
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            //Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.11f)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(horizontal = 20.dp, vertical = 6.dp),

                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .offset(y = (3).dp)
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Default.ArrowBack,
                            modifier = Modifier.offset(x = (-8).dp),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                    Icon(
                        painterResource(id = subIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(37.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = MaterialTheme.shapes.extraSmall
                            )
                    )
                    Text(
                        text = topicTitle,
                        fontFamily = bodyFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
                Text(
                    text = "${currentIndex + 1} / ${totalQuestions}",
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
            }
            //MAIN CONTENT
            Column(modifier = Modifier
                .fillMaxHeight(0.88f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom,


            ){
                //Question
                Box(
                    modifier = Modifier
                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.primary)
                        .padding(vertical = 130.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Text(
                        text = currentQuestion.questionText,
                        fontFamily = bodyFontFamily,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                //Choices
                ChoiceList(
                    choices = currentQuestion.choices,
                    selectedAnswer = selectedAnswer,
                    onAnswerSelected = { index ->
                        selectedAnswer = index
                        answers[currentIndex] = Answered(
                            currentIndex,
                            index,
                            index == currentQuestion.correctAnswerIndex

                        )
                    },
                    isReviewMode = isReviewMode,
                    correctAnswerIndex = currentQuestion.correctAnswerIndex
                )
                //Explanation in review mode
                if (isReviewMode && currentQuestion.explanation.isNotEmpty()) {
                    ExplanationBox(explanation = currentQuestion.explanation)
                }
            }
            //Bottom Nav
            Row(Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
                Arrangement.SpaceBetween) {
                IconButton(onClick = { if (currentIndex > 0) {
                    currentIndex--
                    selectedAnswer = answers[currentIndex]?.selectedAnswerIndex
                } }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Prev")
                }

                if (currentIndex == question.lastIndex && !isReviewMode) {
                    Button(
                        onClick = { showConfirmDialog = true }
                    )
                        {
                        Text("Selesai", color = MaterialTheme.colorScheme.onPrimary)
                        }
                }
                IconButton(onClick = { if (currentIndex < question.lastIndex){
                    currentIndex++
                    selectedAnswer = answers[currentIndex]?.selectedAnswerIndex
                } }) {
                    Icon(
                        Icons.AutoMirrored.Default.ArrowForward,
                        contentDescription = "Next"
                    )
                }

            }
        }
        if (showConfirmDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showConfirmDialog = false },
                title = { Text("Submit Quiz") },
                text = { Text("Ingin Menyelesaikan Quiz?") },
                confirmButton = {
                    Button(
                        onClick = {
                            showConfirmDialog = false
                            isReviewMode = true
                            // Calculate score and call onFinish
                            val score = answers.count { it?.isCorrect == true }
                            onFinish(score)
                        }
                    ) {
                        Text("Selesai")
                    }
                },
                dismissButton = {
                    Button(onClick = { showConfirmDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}


@Composable
fun choiceButton(
    label: String,
    choiceText: String,
    isSelected: Boolean,
    isCorrect: Boolean? = null,
    selectedAnswer: Int?,
    onAnswerSelected: (Int) -> Unit,
    isReviewMode: Boolean,
    index: Int
) {
//    var isSelected by remember { mutableStateOf(isSelected) }
//    var isReviewMode by remember { mutableStateOf(isReviewMode) }

    val backgroundColor = when {
        isCorrect == true && isReviewMode -> Color.Green  // Correct answers in review mode
        isSelected && isReviewMode && isCorrect == false -> MaterialTheme.colorScheme.error  // Selected wrong answers in review mode
        isSelected -> MaterialTheme.colorScheme.primary  // Selected items (not in review mode)
        else -> MaterialTheme.colorScheme.surface
    }
    val labelBgColor = when {
        isSelected -> MaterialTheme.colorScheme.primaryContainer
        else -> MaterialTheme.colorScheme.primary
    }
    val labelTxtColor = when {
        isSelected -> MaterialTheme.colorScheme.onPrimaryContainer
        else -> MaterialTheme.colorScheme.onPrimary
    }

    val textColor = when {
        isSelected -> MaterialTheme.colorScheme.onPrimary
        else -> MaterialTheme.colorScheme.onSurface
    }

    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isReviewMode) {
                if (isSelected) {
                    onAnswerSelected(-1)  // Deselect
                } else {
                    onAnswerSelected(index)  // Select this option
                }
            },
        color = backgroundColor,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = labelBgColor,
                        shape = MaterialTheme.shapes.small
                    )
            ) { Text(
                text=label,
                fontFamily = bodyFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = labelTxtColor
            )}
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = choiceText,
                fontFamily = bodyFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = textColor
            )
        }
    }
}

@Preview
@Composable
fun ChoiceButtonPreview() {
    choiceButton(
        label = "A",
        choiceText = "21",
        isSelected = true,
        isCorrect = false,
        selectedAnswer = 0,
        onAnswerSelected = {},
        isReviewMode = true,
        index = 0
    )
}

@Composable
fun ChoiceList(
    choices: List<String>,
    selectedAnswer: Int?,
    onAnswerSelected: (Int) -> Unit,
    isReviewMode: Boolean,
    correctAnswerIndex: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        choices.forEachIndexed { index, choice ->
            val label = when (index) {
                0 -> "A"
                1 -> "B"
                2 -> "C"
                3 -> "D"
                else -> (index + 1).toString()
            }

            choiceButton(
                label = label,
                choiceText = choice,
                isSelected = selectedAnswer == index,
                isCorrect = if (isReviewMode) index == correctAnswerIndex else null,
                selectedAnswer = selectedAnswer,
                onAnswerSelected = { selectedIndex ->
                    onAnswerSelected(selectedIndex)
                },
                isReviewMode = isReviewMode,
                index = index
            )
        }
    }
}

@Composable
fun ExplanationBox(explanation: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Explanation",
                fontFamily = bodyFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = explanation,
                fontFamily = bodyFontFamily,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}