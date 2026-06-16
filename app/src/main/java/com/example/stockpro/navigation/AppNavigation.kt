package com.example.stockpro.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.stockpro.screens.CatalogoInventarioScreen
import com.example.stockpro.screens.EdicionStockScreen
import com.example.stockpro.screens.IngresoOperarioScreen
import com.example.stockpro.screens.ReporteFinancieroScreen
import com.example.stockpro.viewmodel.StockViewModel

// Objeto que centraliza las rutas de navegación de la aplicación.
// Así evitamos escribir textos repetidos en varias partes del proyecto.
object AppRoutes {
    const val INGRESO = "ingreso"
    const val CATALOGO = "catalogo"
    const val EDICION = "edicion"
    const val REPORTE = "reporte"
}

// Composable principal que contiene el NavHost.
// Aquí se define la navegación entre las 4 pantallas.
@Composable
fun AppNavigation() {

    // Controlador de navegación principal.
    val navController = rememberNavController()

    // ViewModel compartido por todas las pantallas.
    // Este será el único "cerebro" de la app.
    val stockViewModel: StockViewModel = viewModel()

    // NavHost con la pantalla inicial de ingreso.
    NavHost(
        navController = navController,
        startDestination = AppRoutes.INGRESO
    ) {

        // Pantalla 1: ingreso del operario
        composable(AppRoutes.INGRESO) {
            IngresoOperarioScreen(
                navController = navController
            )
        }

        // Pantalla 2: catálogo de inventario
        // Recibe el nombre del operario como parámetro.
        composable(
            route = "${AppRoutes.CATALOGO}/{nombreOperario}",
            arguments = listOf(
                navArgument("nombreOperario") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            // Recuperamos el nombre enviado desde la Pantalla 1
            val nombreOperario =
                backStackEntry.arguments?.getString("nombreOperario") ?: ""

            CatalogoInventarioScreen(
                navController = navController,
                nombreOperario = nombreOperario,
                stockViewModel = stockViewModel
            )
        }

        // Pantalla 3: edición de stock
        // Recibe el id del producto seleccionado.
        composable(
            route = "${AppRoutes.EDICION}/{productoId}",
            arguments = listOf(
                navArgument("productoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            // Recuperamos el id del producto enviado desde el catálogo
            val productoId =
                backStackEntry.arguments?.getInt("productoId") ?: 0

            EdicionStockScreen(
                navController = navController,
                productoId = productoId,
                stockViewModel = stockViewModel
            )
        }

        // Pantalla 4: reporte financiero
        composable(AppRoutes.REPORTE) {
            ReporteFinancieroScreen(
                navController = navController,
                stockViewModel = stockViewModel
            )
        }
    }
}