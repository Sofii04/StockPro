package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.stockpro.navigation.AppNavigation
import com.example.stockpro.ui.theme.StockProTheme

// Actividad principal de la aplicación.
// Desde aquí se carga toda la interfaz de Compose.
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StockProTheme {
                // Llamamos al sistema de navegación principal.
                AppNavigation()
            }
        }
    }
}