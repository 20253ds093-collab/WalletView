package com.example.walletview.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.walletview.repository.WalletRepository

class WalletViewModel : ViewModel() {

    private val repository = WalletRepository()

    private val _gastosCategoria = MutableLiveData<List<Gasto>>()
    val gastosCategoria: LiveData<List<Gasto>> = _gastosCategoria

    private val _gastosMensuales = MutableLiveData<List<GastoMes>>()
    val gastosMensuales: LiveData<List<GastoMes>> = _gastosMensuales

    private val _totalMes = MutableLiveData<Float>()
    val totalMes: LiveData<Float> = _totalMes

    init {
        cargarDatos()
    }

    // Pide los datos al repositorio y los publica para que la vista los observe
    fun cargarDatos() {
        val categorias = repository.obtenerGastosPorCategoria()
        _gastosCategoria.value = categorias
        _gastosMensuales.value = repository.obtenerGastosMensuales()
        _totalMes.value = categorias.sumOf { it.monto.toDouble() }.toFloat()
    }
}