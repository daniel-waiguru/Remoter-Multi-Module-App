package com.danielwaiguru.remoter.shared.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun parseJsonDate(jsonDate: String): Date? {
        val sdf = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss",
            Locale.getDefault()
        ).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }
        return try {
            sdf.parse(jsonDate)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun formatToFullDate(date: Date): String {
        return try {
            val outFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            outFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}