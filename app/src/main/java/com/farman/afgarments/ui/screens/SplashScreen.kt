package com.farman.afgarments.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.farman.afgarments.ui.navigation.Screen
import com.farman.afgarments.ui.theme.AFTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var progress by remember { mutableFloatStateOf(0f) }
    var textAlpha by remember { mutableFloatStateOf(0f) }

    val colors = AFTheme.colors
    val gradient = Brush.verticalGradient(
        colors = listOf(colors.accentPrimary, colors.accentDark)
    )

    LaunchedEffect(Unit) {
        animate(0f, 1f, animationSpec = tween(500)) { value, _ ->
            textAlpha = value
        }
        
        animate(0f, 1f, animationSpec = tween(1300, easing = LinearOutSlowInEasing)) { value, _ ->
            progress = value
        }
        
        delay(400) // Total ~1.7s
        navController.navigate(Screen.Dashboard.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                shape = RoundedCornerShape(28.dp),
                color = Color.White.copy(alpha = 0.2f),
                modifier = Modifier.size(120.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("🧥", fontSize = 60.sp) // Fallback for jacket SVG
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "AF GARMENTS",
                color = Color.White,
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 2.sp,
                modifier = Modifier.alpha(textAlpha)
            )
            Text(
                text = "PRODUCTION TRACKER",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                letterSpacing = 4.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.alpha(textAlpha)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = progress,
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.3f),
                modifier = Modifier
                    .width(200.dp)
                    .height(4.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "by Farman Ahmad",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
    }
}
