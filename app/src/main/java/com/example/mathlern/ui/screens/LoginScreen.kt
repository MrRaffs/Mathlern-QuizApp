package com.example.mathlern.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.*
import com.example.mathlern.R
import com.example.mathlern.ui.theme.MathlernTheme
import com.example.mathlern.ui.theme.bodyFontFamily
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ButtonDefaults.buttonElevation


import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(navController: NavController) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                ) {
                // App icon
                Image(
                    painter = painterResource(id = R.drawable.mathlern_logo),
                    contentDescription = "MathLern Logo",
                    modifier = Modifier
                        .size(500.dp)
                        .align(Alignment.Center)
                )
            }
            //bottom panel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colorScheme.onPrimaryContainer,
                                shape = RoundedCornerShape(
                                    topStart = 16.dp,
                                    topEnd = 16.dp
                                )
                                ),
                contentAlignment = Alignment.Center

                ){
                Column ( modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
                    .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //Title
                    Text(
                        text = "MathLern",
                        fontFamily = bodyFontFamily,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    //Desc
                    Text(
                        text = "Aplikasi kuis matematika interaktif untuk belajar lebih menyenangkan",
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(250.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Button(
                        onClick = {
                            navController.navigate("home")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 50.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                                ),
                        shape = MaterialTheme.shapes.small,
                        elevation = buttonElevation(defaultElevation = 5.dp, pressedElevation = 0.dp),
                    ) {
                        // Google Icon
                        Image(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "Google Icon",
                            modifier = Modifier
                                .size(25.dp)
                        )
                        // Box to center the text
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Sign in with Google",
                                fontFamily = bodyFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }


                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MathlernTheme {
        LoginScreen(navController = rememberNavController())
    }
}