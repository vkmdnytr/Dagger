package com.example.moviedagger.ext

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.moviedagger.R

fun Activity.showAlert(errorMessage: String) {
    if (!this.isFinishing) {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_title)
            .setMessage(errorMessage)
            .setPositiveButton(R.string.ok, null)
            .create()
            .show()
    }
}