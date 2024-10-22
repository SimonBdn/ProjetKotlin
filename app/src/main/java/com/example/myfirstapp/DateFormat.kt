package com.example.myfirstapp

import java.text.SimpleDateFormat
import java.util.Locale

fun DateFormat(dateString: String): String {
    if (dateString.isEmpty()) {
        return "Date inconnue"
    }
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.FRENCH)
    return try {
        val date = inputFormat.parse(dateString)
        date?.let { outputFormat.format(it) } ?: dateString
    } catch (e: Exception) {
        dateString
    }
}