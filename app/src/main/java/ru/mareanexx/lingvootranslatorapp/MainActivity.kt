package ru.mareanexx.lingvootranslatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.lingvootranslatorapp.navigation.AppNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LingvooTranslatorAppTheme {
                AppNavHost()
            }
        }
    }
}