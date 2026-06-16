package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel
import java.util.Locale

// Pantalla 4 del examen.
// Muestra un reporte financiero calculado desde el ViewModel.
@Composable
fun ReporteFinancieroScreen(
    navController: NavController,
    stockViewModel: StockViewModel
) {
    // El cálculo del valor total se solicita al ViewModel.
    // Esto respeta MVVM porque la vista no realiza la lógica de negocio.
    val valorTotal = stockViewModel.calcularValorTotalInventario()

    // También pedimos al ViewModel cuántos productos tienen stock en cero.
    val productosEnCero = stockViewModel.contarProductosEnCero()

    // Formateamos el valor para mostrarlo como dinero con 2 decimales.
    val valorTotalFormateado = String.format(Locale.US, "%.2f", valorTotal)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Reporte Financiero",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Tarjeta principal del reporte.
        // Aquí destacamos el capital invertido total.
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Capital Invertido Total",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "\$$valorTotalFormateado",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Indicador solicitado por el examen:
        // total de productos cuyo stock actual es cero.
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Total de productos con stock en cero",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = productosEnCero.toString(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para regresar al catálogo.
        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Volver al catálogo")
        }
    }
}