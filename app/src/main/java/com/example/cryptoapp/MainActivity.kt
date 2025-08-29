package com.example.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.cryptoapp.data.navigation.MainNavHost
import com.example.cryptoapp.data.navigation.Routes
import com.example.cryptoapp.ui.theme.CryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {
                val navController = rememberNavController()
                MainNavHost(navController, Routes.HomeScreenRoot)
            }
        }
    }
}
