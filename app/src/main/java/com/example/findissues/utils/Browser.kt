package com.example.findissues.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class Browser(private val context: Context) {
    fun launch(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}