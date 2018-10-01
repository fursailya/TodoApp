@file:JvmName("toast")

package com.todo.fursa.util

import android.content.Context
import android.widget.Toast

fun toast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}