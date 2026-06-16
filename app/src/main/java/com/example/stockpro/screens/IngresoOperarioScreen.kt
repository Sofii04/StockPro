package com.example.stockpro.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockpro.R
import com.example.stockpro.navigation.AppRoutes

// Pantalla 1 del examen.
// También funciona como carátula institucional de la aplicación.
@Composable
fun IngresoOperarioScreen(
    navController: NavController
) {
    // Estado local para guardar el nombre escrito por el operario.
    var nombreOperario by remember { mutableStateOf("") }

    // Fondo degradado inspirado en los colores institucionales.
    val fondoInstitucional = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFA726), // Naranja institucional
            Color(0xFF4DD0E1), // Turquesa
            Color(0xFF0288D1), // Azul medio
            Color(0xFF0D47A1)  // Azul profundo inferior
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoInstitucional)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Logo institucional cargado desde res/drawable.
                Image(
                    painter = painterResource(id = R.drawable.logo_ru),
                    contentDescription = "Logo Universitario Rumiñahui",
                    modifier = Modifier.size(95.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Universitario Rumiñahui",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D4EA3),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Estudiante:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D4EA3)
                )

                Text(
                    text = "Acosta Sofía",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Materia:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D4EA3)
                )

                Text(
                    text = "Aplicaciones Móviles",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(26.dp))

                Text(
                    text = "StockPro",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color =  Color(0xFF4DD0E1),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Gestión de Inventario",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0D4EA3),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = nombreOperario,
                    onValueChange = {
                        nombreOperario = it
                    },
                    label = {
                        Text("Nombre del operario")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(22.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    onClick = {
                        // Navega al catálogo enviando el nombre del operario.
                        navController.navigate("${AppRoutes.CATALOGO}/${nombreOperario.trim()}")
                    },
                    // El botón solo se activa si el nombre tiene mínimo 3 caracteres.
                    enabled = nombreOperario.trim().length >= 3
                ) {
                    Text(
                        text = "Ingresar al Sistema",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}