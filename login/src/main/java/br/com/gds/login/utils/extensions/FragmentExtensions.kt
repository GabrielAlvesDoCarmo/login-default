package br.com.gds.login.utils.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.ByteArrayOutputStream

fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.requestPermission(
    permission: String,
    onGranted: () -> Unit,
    onDenied: () -> Unit
): ActivityResultLauncher<String> {
    if (ContextCompat.checkSelfPermission(
            this.requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        onGranted()
        return registerForActivityResult(ActivityResultContracts.RequestPermission()) { } // Retornar um launcher vazio, pois a permissão já está concedida
    }

    // Se a permissão não foi concedida, solicitar a permissão
    return registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onGranted()
        } else {
            onDenied()
        }
    }
}


fun Context.hasPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasPermission(permissions: Array<String>): Boolean {
    return permissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}

fun Bitmap.convertImage(): ByteArray {
    return ByteArrayOutputStream().apply {
        this@convertImage.compress(Bitmap.CompressFormat.JPEG, 100, this@apply)
    }.toByteArray()
}

fun Fragment.navigateTo(direction: NavDirections) {
    findNavController().navigate(direction)
}