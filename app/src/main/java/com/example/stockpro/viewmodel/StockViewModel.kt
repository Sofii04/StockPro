package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.model.Producto

// ViewModel principal de la aplicación.
// En MVVM, el ViewModel contiene el estado y la lógica de negocio.
// Las pantallas solo muestran datos y llaman funciones del ViewModel.
class StockViewModel : ViewModel() {

    // Lista reactiva de productos.
    // mutableStateListOf permite que Jetpack Compose detecte cambios en la lista.
    // Cuando actualizamos un producto, las pantallas se refrescan automáticamente.
    private val _productos = mutableStateListOf<Producto>()

    // Lista pública de solo lectura para que las pantallas puedan consultar los productos.
    val productos: List<Producto>
        get() = _productos

    // Bloque inicial donde cargamos productos de prueba.
    // El examen pide al menos 6 productos iniciales precargados en el código.
    init {
        _productos.addAll(
            listOf(
                Producto(
                    id = 1,
                    nombre = "Laptop Lenovo",
                    descripcion = "Laptop para oficina y control de inventario",
                    precio = 750.0,
                    stockActual = 4
                ),
                Producto(
                    id = 2,
                    nombre = "Mouse Logitech",
                    descripcion = "Mouse inalámbrico ergonómico",
                    precio = 18.5,
                    stockActual = 12
                ),
                Producto(
                    id = 3,
                    nombre = "Teclado Mecánico",
                    descripcion = "Teclado mecánico retroiluminado",
                    precio = 45.0,
                    stockActual = 3
                ),
                Producto(
                    id = 4,
                    nombre = "Monitor Samsung",
                    descripcion = "Monitor LED de 24 pulgadas",
                    precio = 160.0,
                    stockActual = 6
                ),
                Producto(
                    id = 5,
                    nombre = "Cable HDMI",
                    descripcion = "Cable HDMI de alta velocidad",
                    precio = 7.5,
                    stockActual = 0
                ),
                Producto(
                    id = 6,
                    nombre = "Disco SSD 1TB",
                    descripcion = "Unidad de almacenamiento sólido",
                    precio = 95.0,
                    stockActual = 8
                )
            )
        )
    }

    // Busca y devuelve un producto usando su id.
    // Se usará en la pantalla de edición de stock.
    fun obtenerProducto(id: Int): Producto? {
        return _productos.find { producto ->
            producto.id == id
        }
    }

    // Actualiza el stock de un producto específico.
    // No permitimos cantidades negativas para evitar errores de inventario.
    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        if (nuevaCantidad < 0) return

        // Buscamos la posición del producto dentro de la lista.
        val index = _productos.indexOfFirst { producto ->
            producto.id == id
        }

        // Si el producto existe, reemplazamos el objeto por una copia actualizada.
        // Esto ayuda a que Compose detecte el cambio correctamente.
        if (index != -1) {
            val productoActual = _productos[index]
            _productos[index] = productoActual.copy(stockActual = nuevaCantidad)
        }
    }

    // Calcula el valor total del inventario.
    // Fórmula exigida: sumatoria de precio * stock de todos los productos.
    // Este cálculo pertenece al ViewModel, no a las pantallas Composable.
    fun calcularValorTotalInventario(): Double {
        return _productos.sumOf { producto ->
            producto.precio * producto.stockActual
        }
    }

    // Devuelve los productos con stock menor a 5.
    // Esto se usará para el filtro "Stock Crítico".
    fun obtenerProductosEnRiesgo(): List<Producto> {
        return _productos.filter { producto ->
            producto.stockActual < 5
        }
    }

    // Cuenta los productos que tienen stock en cero.
    // Esto se mostrará en la pantalla de reporte financiero.
    fun contarProductosEnCero(): Int {
        return _productos.count { producto ->
            producto.stockActual == 0
        }
    }
}