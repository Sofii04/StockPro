package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

// Pantalla 3 del examen.
// Permitirá modificar el stock del producto seleccionado.
@Composable
fun EdicionStockScreen(
    navController: NavController,
    productoId: Int,
    stockViewModel: StockViewModel
) {
    // Obtenemos el producto desde el ViewModel usando su id.
    val producto = stockViewModel.obtenerProducto(productoId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edición de Stock")

        Text(text = "Producto ID: $productoId")
        Text(text = "Nombre: ${producto?.nombre ?: "No encontrado"}")
        Text(text = "Stock actual: ${producto?.stockActual ?: 0}")

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text("Guardar y Volver")
        }
    }
}