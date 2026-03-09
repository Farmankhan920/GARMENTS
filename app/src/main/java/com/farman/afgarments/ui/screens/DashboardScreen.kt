package com.farman.afgarments.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farman.afgarments.ui.theme.AFTheme

@Composable
fun DashboardScreen(navController: NavController) {
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
            text = "Dashboard",
            color = colors.textPrimary,
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Hero Card (Today's Production)
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colors.accentPrimary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Today's Production",
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text("Pieces", color = Color.White.copy(alpha=0.7f))
                        Text("0", color = Color.White, style = MaterialTheme.typography.titleLarge)
                    }
                    Column {
                        Text("Earned", color = Color.White.copy(alpha=0.7f))
                        Text("₹0", color = Color.White, style = MaterialTheme.typography.titleLarge)
                    }
                    Column {
                        Text("Expense", color = Color.White.copy(alpha=0.7f))
                        Text("₹0", color = Color.White.copy(alpha=0.9f), style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Last 7 Days Production",
            color = colors.textPrimary,
            style = MaterialTheme.typography.titleMedium
        )
        // Placeholder for Chart
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
                .background(colors.surface, RoundedCornerShape(12.dp))
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Workers",
            color = colors.textPrimary,
            style = MaterialTheme.typography.titleMedium
        )
        // Worker list placeholder
        Text("No workers yet. Add them in settings.", color = colors.textSecondary)
    }
}
