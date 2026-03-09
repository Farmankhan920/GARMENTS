package com.farman.afgarments.utils

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object FormatUtil {
    fun formatCurrency(amount: Double, symbol: String = "₹"): String {
        val format = NumberFormat.getNumberInstance(Locale.getDefault())
        format.minimumFractionDigits = 0
        format.maximumFractionDigits = 2
        return "$symbol${format.format(amount)}"
    }

    fun formatDate(dateString: String, inFormat: String = Constants.DATE_FORMAT, outFormat: String = Constants.DISPLAY_DATE_FORMAT): String {
        return try {
            val parser = SimpleDateFormat(inFormat, Locale.getDefault())
            val formatter = SimpleDateFormat(outFormat, Locale.getDefault())
            val date = parser.parse(dateString)
            date?.let { formatter.format(it) } ?: dateString
        } catch (e: Exception) {
            dateString
        }
    }

    fun getCurrentDate(format: String = Constants.DATE_FORMAT): String {
        return SimpleDateFormat(format, Locale.getDefault()).format(Date())
    }
}
