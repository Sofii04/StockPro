package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

// Pantalla 4 del examen.
// Muestra el valor total del inventario y la cantidad de productos con stock en cero.
@Composable
fun ReporteFinancieroScreen(
    navController: NavController,
    stockViewModel: StockViewModel
) {
    // Pedimos los cálculos al ViewModel, no a la vista.
    val valorTotal = stockViewModel.calcularValorTotalInventario()
    val productosEnCero = stockViewModel.contarProductosEnCero()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Reporte Financiero")
        Text(text = "Capital Invertido Total: $${"%.2f".format(valorTotal)}")
        Text(text = "Productos con stock en cero: $productosEnCero")

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Volver al catálogo")
        }
    }
}