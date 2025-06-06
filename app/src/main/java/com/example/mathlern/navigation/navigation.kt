package com.example.mathlern.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
//Screen
import com.example.mathlern.ui.screens.LoginScreen
import com.example.mathlern.ui.screens.HomeScreen
import com.example.mathlern.ui.screens.LatihanQuizScreen
import com.example.mathlern.ui.screens.SubTopicScreen
import com.example.mathlern.ui.screens.TantanganQuizScreen
import com.example.mathlern.utils.getDummyMathQuestions

//var isLoggedIn = false
//val start = if (isLoggedIn) "home" else "login"

@Composable
fun AppNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navHostController)
        }
        composable("home") {
            HomeScreen(navHostController)
        }
        composable(
            route = "subtopic/{label}/{subtitle}/{subIcon}",
            arguments = listOf(
                navArgument("label") { type = NavType.StringType },
                navArgument("subtitle") { type = NavType.StringType },
                navArgument("subIcon") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val label = navBackStackEntry.arguments?.getString("label") ?: ""
            val subtitle = navBackStackEntry.arguments?.getString("subtitle") ?: ""
            val icon = navBackStackEntry.arguments?.getInt("subIcon") ?: 0
            SubTopicScreen(
                title = label,
                subtitle = subtitle,
                subIcon = icon,
                navController = navHostController,
                onBack = { navHostController.popBackStack() }
            )
        }

        composable(
            route = "quiz/{topicTitle}/{label}/{quizType}/{level}/{subIcon}",
            arguments = listOf(
                navArgument("topicTitle") { type = NavType.StringType },
                navArgument("label") { type = NavType.StringType },
                navArgument("quizType") { type = NavType.StringType },
                navArgument("level") { type = NavType.StringType },
                navArgument("subIcon") { type = NavType.IntType }
            )
        ){ navBackStackEntry ->
            val topicTitle = navBackStackEntry.arguments?.getString("topicTitle") ?: ""
            val title = navBackStackEntry.arguments?.getString("label") ?: ""
            val quizType = navBackStackEntry.arguments?.getString("quizType") ?: ""
            val level = navBackStackEntry.arguments?.getString("level") ?: ""
            val icon = navBackStackEntry.arguments?.getInt("subIcon") ?: 0

            val questions = getDummyMathQuestions(topicTitle, quizType, level)

            // Handle the case where the arguments are null or empty
            if (title.isEmpty() || quizType.isEmpty() || level.isEmpty()) {
                // Handle error case
                return@composable
            }

            if(quizType == "latihan") {
                 LatihanQuizScreen(
                     topicTitle = title,
                     quizType = quizType,
                     level = level,
                     subIcon = icon,
                     onBack = { navHostController.popBackStack() },
                     onFinish = { score -> /* Handle finish */ },
                     question = questions
                 )
            } else if(quizType == "tantangan") {
                TantanganQuizScreen(
                    topicTitle = title,
                    quizType = quizType,
                    level = level,
                    subIcon = icon,
                    onBack = { navHostController.popBackStack() },
                    onFinish = { score -> /* Handle finish */ },
                    question = questions
                )
            }

        }
    }
}