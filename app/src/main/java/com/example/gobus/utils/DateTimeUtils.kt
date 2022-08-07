package com.example.gobus.utils

import java.text.SimpleDateFormat
import java.util.*

fun parseDateTimeToTime(date: Date?): String{
    val parser = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
    val formatter = SimpleDateFormat("HH:mm")
    return formatter.format(parser.parse(date.toString()))
}