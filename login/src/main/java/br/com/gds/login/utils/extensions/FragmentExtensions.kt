package br.com.gds.login.utils.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.icu.text.Transliterator.Position
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import br.com.gds.login.R
import br.com.gds.login.feature.container.model.DefaultColors
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


fun AppCompatImageButton.changeColorImageButton(isBorder: Boolean, iconDrawable: Int, defaultColors: DefaultColors){
    this.background = if (isBorder) context.createCustomShapeDrawable(defaultColors) else null
    this.setImageDrawable(context.changeDrawableColor(iconDrawable,defaultColors.secondaryColor))
}


fun Context.createCustomShapeDrawable(defaultColors: DefaultColors): GradientDrawable {
    val strokeWidth = this.resources.getDimensionPixelSize(br.com.gds.design_system.R.dimen.size_1dp)
    val strokeColor = ContextCompat.getColor(this, defaultColors.secondaryColor)
    val solidColor = ContextCompat.getColor(this, defaultColors.primaryColor)
    val cornerRadiusDefault = this.resources.getDimension(R.dimen.size_8dp)

    return GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        setStroke(strokeWidth, strokeColor)
        setColor(solidColor)
        cornerRadius = cornerRadiusDefault
    }
}

fun Context.changeDrawableColor(
    @DrawableRes iconDrawable: Int,
    @ColorRes color: Int,
) = ContextCompat.getDrawable(this, iconDrawable)?.apply {
    DrawableCompat.setTint(this, ContextCompat.getColor(this@changeDrawableColor, color))
}



fun AppCompatCheckBox.changeCheckBoxColor(defaultColors: DefaultColors) {
    context.getColorStateList(defaultColors.secondaryColor).apply {
        buttonTintList = this
        setTextColor(this)
    }
}


fun EditText.setStartDrawableWithTint(
    context: Context,
    @DrawableRes drawableRes: Int,
    @ColorRes tintColorRes: Int,
    drawablePaddingRes: Int = R.dimen.size_8dp,
) {
    // Carregar o drawable
    val drawable: Drawable? = ContextCompat.getDrawable(context, drawableRes)
    // Aplicar o tint na cor desejada
    drawable?.setTint(ContextCompat.getColor(context, tintColorRes))
    // Definir o drawableStart programaticamente
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
    // Ajustar o padding entre o drawable e o texto
    this.compoundDrawablePadding = resources.getDimensionPixelSize(drawablePaddingRes)
}