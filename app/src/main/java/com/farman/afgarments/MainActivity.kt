package com.farman.afgarments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.farman.afgarments.ui.navigation.AppNavigation
import com.farman.afgarments.ui.theme.AFGarmentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AFGarmentsTheme {
                AppNavigation()
            }
        }
    }
}
