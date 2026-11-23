package com.example.walletview.model

// Gasto total de una categoría (para la gráfica de pastel)
data class Gasto(val categoria: String, val monto: Float)

// Gasto total de un mes (para la gráfica de barras)
data class GastoMes(val mes: String, val total: Float)
