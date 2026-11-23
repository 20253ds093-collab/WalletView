package com.example.walletview.repository

import com.example.walletview.model.Gasto
import com.example.walletview.model.GastoMes

// Repositorio con datos de ejemplo
class WalletRepository {

    fun obtenerGastosPorCategoria(): List<Gasto> = listOf(
        Gasto("Comida", 3200f),
        Gasto("Transporte", 1500f),
        Gasto("Renta", 5000f),
        Gasto("Ocio", 1200f),
        Gasto("Servicios", 1800f)
    )

    fun obtenerGastosMensuales(): List<GastoMes> = listOf(
        GastoMes("Jun", 11800f),
        GastoMes("Jul", 12400f),
        GastoMes("Ago", 10900f),
        GastoMes("Sep", 13100f),
        GastoMes("Oct", 12200f),
        GastoMes("Nov", 12700f)
    )
}