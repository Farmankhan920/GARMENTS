package com.farman.afgarments.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farman.afgarments.ui.theme.AFTheme

@Composable
fun SettingsScreen(navController: NavController) {
    val colors = AFTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(16.dp)
    ) {
        Text(
            text = "AF GARMENTS",
            color = colors.accentPrimary,
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Settings",
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text("App settings & configurations will appear here.", color = colors.textSecondary)
    }
}
