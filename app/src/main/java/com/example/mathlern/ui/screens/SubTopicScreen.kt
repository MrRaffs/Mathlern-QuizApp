package com.example.mathlern.ui.screens

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathlern.ui.theme.bodyFontFamily
import androidx.navigation.NavController
import androidx.compose.material3.IconButton

@Composable
fun SubTopicScreen(
    title: String,
    subtitle: String,
    subIcon: Int,
    onBack: () -> Unit,
    navController: NavController
//    onLevelClick: (String, leveltype: String) -> Unit
) {
    Surface {
        Column(){
            //Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.11f)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(horizontal = 20.dp, vertical = 8.dp),

                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "MathLern",
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onPrimary

                )
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            // "profile"
                            //TODO: Navigate to profile screen
                        }
                )
            }
            //Main content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 10.dp, horizontal = 25.dp),
            ){
                IconButton(onClick = onBack, modifier = Modifier
                    .offset(x = (-15).dp),
                ) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                }
                //Title Header
                Row(modifier = Modifier
                    .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    //Icon
                    Icon(
                        painterResource(id = subIcon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(55.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = MaterialTheme.shapes.extraSmall
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    //Title
                    Text(
                        text = title,
                        fontFamily = bodyFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                //Subtitle
                Text(
                    text = subtitle,
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                //Subtopic list
                //Latihan
                QuizEntrySection(
                    TopicTitle = title,
                    subIcon = subIcon,
                    sectionTitle = "Latihan",
                    quizType = "latihan",
                    quizButton = listOf(
                        QuizEntry("Latihan I", "I"),
                        QuizEntry("Latihan II", "II"),
                        QuizEntry("Latihan III", "III")
                    ),
                    navController = navController
                )
                //Tantangan Waktu
                QuizEntrySection(
                    TopicTitle = title,
                    subIcon = subIcon,
                    sectionTitle = "Tantangan Waktu",
                    quizType = "tantangan",
                    quizButton = listOf(
                        QuizEntry("Level I", "I"),
                        QuizEntry("Level II", "II"),
                        QuizEntry("Level III", "III")
                    ),
                    navController = navController
                )
            }
        }

    }
}

//@Preview
//@Composable
//fun SubTopicScreenPreview() {
//    SubTopicScreen("Penjumlahan", "subtitle", R.drawable.ic_addition, onBack = { })
//
//}

@Composable
fun QuizEntrySection(
    TopicTitle: String,
    subIcon: Int,
    sectionTitle: String,
    quizType: String,
    quizButton: List<QuizEntry>,
    navController: NavController
) {
    Column () {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = sectionTitle,
            fontFamily = bodyFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
        quizButton.forEach { quiz ->
            QuizEntryButton(
                label = quiz.label,
                level = quiz.level,
                quizType = quizType,
                topicTitle = TopicTitle,
                subIcon = subIcon,
                navController = navController
            )
        }
    }
}

data class QuizEntry(
    val label: String,
    val level: String,
)
@Composable
fun QuizEntryButton(
    label: String,
    level: String,
    quizType: String,
    topicTitle: String,
    subIcon: Int,
    navController: NavController
) {
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable {  navController.navigate("quiz/${topicTitle}/${label}/${quizType}/${level}/${subIcon}")
                        },
        color = MaterialTheme.colorScheme.onPrimary,
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
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small
                    )
            ) { Text(
                text=level,
                fontFamily = bodyFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )}
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontFamily = bodyFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}