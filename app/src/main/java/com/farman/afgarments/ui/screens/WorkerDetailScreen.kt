package com.farman.afgarments.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.farman.afgarments.ui.theme.AFTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkerDetailScreen(navController: NavController, workerName: String) {
    val colors = AFTheme.colors

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(workerName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colors.background,
                    titleContentColor = colors.textPrimary,
                    navigationIconContentColor = colors.textPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Details for $workerName will be shown here.", color = colors.textSecondary)
        }
    }
}
