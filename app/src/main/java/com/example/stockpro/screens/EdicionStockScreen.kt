package com.example.stockpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockpro.viewmodel.StockViewModel

// Pantalla 3 del examen.
// Permite editar el stock de un producto seleccionado desde el catálogo.
@Composable
fun EdicionStockScreen(
    navController: NavController,
    productoId: Int,
    stockViewModel: StockViewModel
) {
    // Obtenemos el producto desde el ViewModel usando el id recibido por navegación.
    // Como la lista del ViewModel es reactiva, si el stock cambia esta pantalla se actualiza.
    val producto = stockViewModel.obtenerProducto(productoId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Si el producto no existe, mostramos un mensaje de seguridad.
        if (producto == null) {
            Text(text = "Producto no encontrado")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Volver")
            }

        } else {

            Text(
                text = "Edición de Stock",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nombre del producto seleccionado.
            Text(
                text = producto.nombre,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Descripción del producto.
            Text(
                text = producto.descripcion,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Stock actual en tamaño grande, como solicita el examen.
            Text(
                text = "Stock actual",
                fontSize = 18.sp
            )

            Text(
                text = producto.stockActual.toString(),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Botón para restar una unidad al stock.
                // Se deshabilita si el stock es cero para evitar inventario negativo.
                Button(
                    onClick = {
                        stockViewModel.actualizarStock(
                            id = producto.id,
                            nuevaCantidad = producto.stockActual - 1
                        )
                    },
                    enabled = producto.stockActual > 0
                ) {
                    Text("-1")
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Botón para sumar una unidad al stock actual.
                Button(
                    onClick = {
                        stockViewModel.actualizarStock(
                            id = producto.id,
                            nuevaCantidad = producto.stockActual + 1
                        )
                    }
                ) {
                    Text("+1")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // popBackStack regresa a la pantalla anterior.
            // Al volver al catálogo, el nuevo stock se verá actualizado
            // porque el estado vive en el ViewModel compartido.
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Guardar y Volver")
            }
        }
    }
}