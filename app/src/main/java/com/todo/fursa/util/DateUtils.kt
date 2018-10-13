@file:JvmName("DateUtils")

package com.todo.fursa.util

import java.sql.Timestamp
import java.text.SimpleDateFormat

fun toTimestamp(time: String, date: String): Long {
    val dateFormatter = SimpleDateFormat("HH:mm dd.MM.yyyy")
    val sb = StringBuffer()
    sb.append(time).append(" ").append(date)
    val date = dateFormatter.parse(sb.toString())
    return Timestamp(date.time).time
}

fun fromTimestamp(timestamp: Long): String? {
    val dateFormatter = SimpleDateFormat("HH:mm dd.MM.yyyy")
    val date = dateFormatter.format(timestamp)
    return date.toString()
}

