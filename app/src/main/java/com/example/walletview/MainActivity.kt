package com.example.walletview

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.walletview.databinding.ActivityMainBinding
import com.example.walletview.model.Gasto
import com.example.walletview.model.GastoMes
import com.example.walletview.model.WalletViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WalletViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // La vista solo observa al ViewModel (MVVM)
        viewModel.totalMes.observe(this) { total ->
            binding.tvTotal.text = "Total del mes: $${"%,.2f".format(total)}"
        }
        viewModel.gastosCategoria.observe(this) { mostrarPastel(it) }
        viewModel.gastosMensuales.observe(this) { mostrarBarras(it) }
    }

    // Gráfica de pastel: gastos por categoría
    private fun mostrarPastel(gastos: List<Gasto>) {
        val entradas = gastos.map { PieEntry(it.monto, it.categoria) }

        val dataSet = PieDataSet(entradas, "Categorías").apply {
            colors = ColorTemplate.MATERIAL_COLORS.toList()
            valueTextSize = 12f
            valueTextColor = Color.WHITE
        }

        binding.pieChart.apply {
            data = PieData(dataSet)
            description.isEnabled = false
            centerText = "Gastos"
            setEntryLabelColor(Color.BLACK)
            animateY(1000)   // animación incluida en la librería
            invalidate()
        }
    }

    // Gráfica de barras: gasto total por mes
    private fun mostrarBarras(meses: List<GastoMes>) {
        val entradas = meses.mapIndexed { i, m -> BarEntry(i.toFloat(), m.total) }

        val dataSet = BarDataSet(entradas, "Gasto mensual").apply {
            colors = ColorTemplate.COLORFUL_COLORS.toList()
            valueTextSize = 10f
        }

        binding.barChart.apply {
            data = BarData(dataSet)
            description.isEnabled = false
            xAxis.valueFormatter = IndexAxisValueFormatter(meses.map { it.mes })
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1f
            axisRight.isEnabled = false
            animateY(1000)
            invalidate()
        }
    }
}
