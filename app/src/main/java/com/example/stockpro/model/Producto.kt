package com.example.stockpro.model

// Data class que representa un producto dentro del inventario.
// Este modelo pertenece a la capa "Model" dentro de la arquitectura MVVM.
data class Producto(
    val id: Int,                 // Identificador único del producto
    val nombre: String,          // Nombre visible del producto
    val descripcion: String,     // Descripción breve del producto
    val precio: Double,          // Precio unitario del producto
    var stockActual: Int         // Cantidad actual disponible en inventario
)