package com.example.cryptoapp.data.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone


fun parseTimestamp(timestamp: String): Pair<String, String> {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdf.parse(timestamp)
        val hour = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date!!)
        val dateStr = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date)
        hour to dateStr
    } catch (e: Exception) {
        "--:--" to "--/--/----"
    }
}