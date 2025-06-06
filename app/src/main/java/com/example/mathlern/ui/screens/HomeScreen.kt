package com.example.mathlern.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mathlern.R
import com.example.mathlern.ui.theme.bodyFontFamily


@Composable
fun HomeScreen(navController: NavController) {
    Surface {
        Column() {
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
                    .padding(vertical = 10.dp, horizontal = 10.dp),
            ) {
                TopicSection(
                    title = "Operasi Dasar",
                    subtitle = "Sekolah Dasar (Kelas 1 - 3)",
                    headerIcon = R.drawable.ic_pemdas,
                    topics = listOf(
                        SubTopic("Penjumlahan", R.drawable.ic_addition), //onSubTopicClick("penjumlahan")
                        SubTopic("Pengurangan", R.drawable.ic_substraction),
                        SubTopic("Perkalian", R.drawable.ic_multiplication),
                        SubTopic("Pembagian", R.drawable.ic_division),
                        SubTopic("Urutan Operasi (PEMDAS)", R.drawable.ic_pemdas)
                    ),
                    navController = navController
                )

                TopicSection(
                    title = "Bilangan & \nOperasi Lanjutan",
                    subtitle = "Sekolah Dasar (Kelas 4 - 6)",
                    headerIcon = R.drawable.ic_pemdas,
                    topics = listOf(
                        SubTopic("Pecahan", R.drawable.ic_addition), //onSubTopicClick("penjumlahan")
                        SubTopic("Desimal dan Persentase", R.drawable.ic_substraction),
                        SubTopic("Faktor (FPB) dan Kelipatan (KPK)", R.drawable.ic_multiplication),
                        SubTopic("Pangkat dan Akar", R.drawable.ic_division)
                    ),
                    navController = navController

                )

                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

@Composable
fun TopicSection(
    title: String,
    subtitle: String,
    headerIcon: Int,
    topics: List<SubTopic>,
    navController: NavController
) {
    Column (modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = headerIcon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontFamily = bodyFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium.merge(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(start = 8.dp).padding(vertical = 5.dp)
        )

        topics.forEach{ topic ->
            SubTopicButton(
                label = topic.label,
                parentSubtitle = subtitle,
                subIcon = topic.subIcon,
                navController = navController
            )
        }
    }
}

//@Preview(showBackground = true, device = "id:pixel_5")
//@Composable
//fun TopicSectionPreview() {
//    TopicSection(
//        title = "Operasi Dasar",
//        subtitle = "Sekolah Dasar Kelas 1 - 3 SD",
//        headerIcon = R.drawable.ic_pemdas,
//        topics = listOf(
//            SubTopic("Penjumlahan", R.drawable.ic_addition),
//            SubTopic("Pengurangan", R.drawable.ic_substraction),
//            SubTopic("Perkalian", R.drawable.ic_multiplication) ,
//            SubTopic("Pembagian", R.drawable.ic_division),
//            SubTopic("Urutan Operasi (PEMDAS)", R.drawable.ic_pemdas)
//        ),
//        navController = NavController(null)
//    )
//
//}

@Composable
fun SubTopicButton(
    label: String,
    parentSubtitle: String,
    subIcon: Int,
    navController: NavController
) {
    Surface (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { navController.navigate("subtopic/${label}/${parentSubtitle}/${subIcon}") },
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
            Icon(
                painterResource(id = subIcon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.small
                    )
            )
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

data class SubTopic(
    val label: String,
    val subIcon: Int,
)
