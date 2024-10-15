package br.com.gds.login.utils.extensions

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun appendMessageToFile(context: Context, message: String) {
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
    val telemetriaFolder = File(storageDir, "storage/Documents/fileTelemetria")
    if (!telemetriaFolder.exists()) {
        telemetriaFolder.mkdirs()
    }

    val file = File(telemetriaFolder, "Telemetria_login.txt")

    try {
        if (!file.exists()) {
            file.createNewFile()
        }
        val dateFormat = SimpleDateFormat("[dd-MM-yyyy HH:mm:ss.SSS] <------->", Locale.getDefault())
        val timestamp = dateFormat.format(Date())

        file.appendText("$timestamp - $message\n")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}