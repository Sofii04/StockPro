package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.navigation.AppRoutes

// Pantalla 1 del examen.
// Permite ingresar el nombre del operario.
@Composable
fun IngresoOperarioScreen(
    navController: NavController
) {
    // Estado local para guardar el nombre escrito por el usuario.
    var nombreOperario by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido a StockPro")

        OutlinedTextField(
            value = nombreOperario,
            onValueChange = { nombreOperario = it },
            label = { Text("Nombre del operario") }
        )

        Button(
            onClick = {
                // Navega al catálogo enviando el nombre del operario.
                navController.navigate("${AppRoutes.CATALOGO}/$nombreOperario")
            },
            enabled = nombreOperario.trim().length >= 3
        ) {
            Text("Ingresar al Sistema")
        }
    }
}