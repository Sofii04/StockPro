package com.example.stockpro.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockpro.model.Producto
import com.example.stockpro.navigation.AppRoutes
import com.example.stockpro.viewmodel.StockViewModel

// Pantalla 2 del examen.
// Muestra el catálogo de inventario, permite filtrar productos
// y navegar a la pantalla de edición de stock.
@Composable
fun CatalogoInventarioScreen(
    navController: NavController,
    nombreOperario: String,
    stockViewModel: StockViewModel
) {
    // Estado local para saber si el usuario quiere ver todo
    // o solo los productos con stock crítico.
    var mostrarStockCritico by remember { mutableStateOf(false) }

    // Si mostrarStockCritico es true, usamos los productos en riesgo.
    // Si es false, mostramos todos los productos.
    val productosAMostrar: List<Producto> = if (mostrarStockCritico) {
        stockViewModel.obtenerProductosEnRiesgo()
    } else {
        stockViewModel.productos
    }

    Scaffold(
        // Botón flotante para ir al reporte financiero.
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppRoutes.REPORTE)
                }
            ) {
                Text("Reporte")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Texto superior solicitado por el examen.
            Text(text = "Operario: $nombreOperario")

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Catálogo de Inventario")

            Spacer(modifier = Modifier.height(16.dp))

            // Botones de filtro: Ver Todo y Stock Crítico.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        mostrarStockCritico = false
                    }
                ) {
                    Text("Ver Todo")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        mostrarStockCritico = true
                    }
                ) {
                    Text("Stock Crítico")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista eficiente para mostrar productos.
            // LazyColumn es ideal cuando tenemos varios elementos.
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productosAMostrar) { producto ->

                    // Cada producto se muestra dentro de una tarjeta.
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Al tocar la tarjeta, navegamos a edición
                                // enviando el id del producto seleccionado.
                                navController.navigate("${AppRoutes.EDICION}/${producto.id}")
                            }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = producto.nombre)

                            Text(
                                text = "Precio unitario: $${"%.2f".format(producto.precio)}"
                            )

                            // Si el stock es menor a 5, mostramos el texto en rojo.
                            Text(
                                text = "Stock actual: ${producto.stockActual}",
                                color = if (producto.stockActual < 5) {
                                    Color.Red
                                } else {
                                    Color.Unspecified
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}