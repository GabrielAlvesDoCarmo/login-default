package br.com.gds.login.utils.extensions.edittext

import android.content.Context
import android.content.res.ColorStateList
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.gds.login.feature.container.model.DefaultColors
import br.com.gds.login.utils.extensions.createCustomShapeDrawable
import br.com.gds.login.utils.extensions.setStartDrawableWithTint
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val SIMPLE_REGEX = "[^a-zA-Z0-9@._\\-+]"
private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$"
private const val PASSWORD_REGEX =
    "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
private const val LOWERCASE_REGEX = ".*[a-z].*"
private const val UPPERCASE_REGEX = ".*[A-Z].*"
private const val NUMBER_REGEX = ".*[0-9].*"
private const val SPECIAL_CHAR_REGEX = ".*[@#\$%^&+=].*"

fun TextInputEditText.validateEmailField(): StateFlow<EditTextState> {
    val state = MutableStateFlow<EditTextState>(EditTextState.Empty)
    this.addTextChangedListener { editable ->
        val text = editable.toString()
        val regex = Regex(SIMPLE_REGEX)
        val emailRegex = Regex(EMAIL_REGEX)
        when {
            text.isEmpty() -> state.value = EditTextState.Empty
            text.isBlank() -> state.value = EditTextState.Empty
            text.contains(regex) -> state.value = EditTextState.Invalid("Caracter invalido")
            !android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches() -> state.value =
                EditTextState.Invalid("E-mail inválido")

            !text.contains(emailRegex) -> state.value = EditTextState.Valid
            else -> state.value = EditTextState.Valid

        }
    }
    return state.asStateFlow()
}

fun TextInputEditText.validatePasswordField(): StateFlow<EditTextState> {
    val state = MutableStateFlow<EditTextState>(EditTextState.Empty)
    this.addTextChangedListener { editable ->
        val text = editable.toString()
        when {
            text.isEmpty() -> state.value = EditTextState.Empty
            text.isBlank() -> state.value = EditTextState.Empty
            !text.contains(Regex(LOWERCASE_REGEX)) -> state.value =
                EditTextState.Invalid("A senha deve conter pelo menos uma letra minuscula")

            !text.contains(Regex(UPPERCASE_REGEX)) -> state.value =
                EditTextState.Invalid("A senha deve conter pelo menos uma letra maiuscula")

            !text.contains(Regex(NUMBER_REGEX)) -> state.value =
                EditTextState.Invalid("A senha deve conter pelo menos um numero")

            !text.contains(Regex(SPECIAL_CHAR_REGEX)) -> state.value =
                EditTextState.Invalid("A senha deve conter pelo menos um caracter especial")

            text.length <= 7 -> state.value =
                EditTextState.Invalid("A senha deve conter no minimo 8 caracteres")

            text.length >= 20 -> state.value =
                EditTextState.Invalid("A senha deve conter no maximo 20 caracteres")
//            !text.contains(Regex(PASSWORD_REGEX)) -> state.value = EditTextState.Invalid("A senha deve conter pelo menos uma letra maiuscula, uma letra minuscula, um numero e um caracter especial")
            else -> state.value = EditTextState.Valid

        }
    }
    return state.asStateFlow()
}

fun TextInputLayout.applyStyle(@StyleRes styleRes: Int) {
    val context = this.context
    val typedArray = context.obtainStyledAttributes(
        styleRes,
        com.google.android.material.R.styleable.TextInputLayout
    )

    val boxStrokeColor = typedArray.getColor(
        com.google.android.material.R.styleable.TextInputLayout_boxStrokeColor,
        0
    )
    val startIconTint = typedArray.getColor(
        com.google.android.material.R.styleable.TextInputLayout_startIconTint,
        0
    )
    val endIconTint =
        typedArray.getColor(com.google.android.material.R.styleable.TextInputLayout_endIconTint, 0)

    this.boxStrokeColor = boxStrokeColor
    this.setStartIconTintList(ColorStateList.valueOf(startIconTint))
    this.setEndIconTintList(ColorStateList.valueOf(endIconTint))

    typedArray.recycle()
}

inline fun <reified T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this as LiveData<T>
}

fun AppCompatEditText.setMaskEdit(
    typeMask: EditTextMask
) {
    this.doOnTextChanged { text, _, _, _ ->
        val formattedText = when (typeMask) {
            EditTextMask.CEP -> formatCEP(text.toString())
            EditTextMask.PHONE -> formatPhone(text.toString())
            EditTextMask.CPF -> formatCPF(text.toString())
            EditTextMask.PLACA -> formatPlacaNormal(text.toString())
            EditTextMask.PLACA_MERCOSUL -> formatPlacaMercosul(text.toString())
        }
        if (formattedText != text.toString()) {
            this.setText(formattedText)
            this.setSelection(formattedText.length)
        }
    }
}

fun formatPhone(text: String): String {
    val digits = text.replace(Regex("\\D"), "")
    return when (digits.length) {
        0 -> ""
        in 1..2 -> "(${digits}"
        in 3..7 -> "(${digits.substring(0, 2)}) ${digits.substring(2)}"
        in 8..11 -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7)}"
        else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7, 11)}"
    }
}

fun formatCEP(text: String): String {
    val digits = text.replace(Regex("\\D"), "")
    return when (digits.length) {
        0 -> ""
        in 1..5 -> "$digits"
        in 6..8 -> "${digits.substring(0, 5)}-${digits.substring(5)}"
        else -> "${digits.substring(0, 5)}-${digits.substring(5, 8)}"
    }
}

fun formatCPF(text: String): String {
    val digits = text.replace(Regex("\\D"), "")
    return when (digits.length) {
        0 -> ""
        in 1..3 -> digits
        in 4..6 -> "${digits.substring(0, 3)}.${digits.substring(3)}"
        in 7..9 -> "${digits.substring(0, 3)}.${digits.substring(3, 6)}.${digits.substring(6)}"
        in 10..11 -> "${digits.substring(0, 3)}.${digits.substring(3, 6)}.${digits.substring(6, 9)}-${digits.substring(9)}"
        else -> "${digits.substring(0, 3)}.${digits.substring(3, 6)}.${digits.substring(6, 9)}-${digits.substring(9, 11)}"
    }
}

fun formatPlacaNormal(text: String): String {
    val cleanedText = text
        .replace(Regex("[^a-zA-Z0-9]"), "")
        .uppercase()
    return when (cleanedText.length) {
        0 -> ""
        in 1..4 -> cleanedText
        in 5..7 -> "${cleanedText.substring(0, 4)}-${cleanedText.substring(4)}"
        else -> "${cleanedText.substring(0, 4)}-${cleanedText.substring(4, 7)}"
    }
}

fun formatPlacaMercosul(text: String): String {
    val cleanedText = text.replace(Regex("[^a-zA-Z0-9]"), "").uppercase()
    return when (cleanedText.length) {
        0 -> ""
        in 1..3 -> cleanedText
        4 -> "${cleanedText.substring(0, 3)}${
            if (cleanedText[3].isLetter()) "" 
            else cleanedText[3]
        }"
        in 5..7 -> "${cleanedText.substring(0, 3)}${cleanedText.substring(3, 4)}${
            if (cleanedText.substring(4, 5).matches(Regex("[a-zA-Z]"))) cleanedText.substring(4, 5) 
            else ""
        }${
            if (cleanedText.substring(5).matches(Regex("[0-9]"))) cleanedText.substring(5)
            else ""
        }"
        else -> "${cleanedText.substring(0, 3)}${cleanedText.substring(3, 4)}${cleanedText.substring(4, 5)}${cleanedText.substring(5, 7)}"
    }
}

fun AppCompatEditText.setupEdit(context: Context, defaultColors: DefaultColors, icon: Int) {
    background = context.createCustomShapeDrawable(defaultColors)

    setStartDrawableWithTint(
        context = context,
        drawableRes = icon,
        tintColorRes = defaultColors.secondaryColor
    )
    setHintTextColor(context.getColor(defaultColors.lettersColors.hint))
}
enum class EditTextMask {
    CEP, PHONE, CPF, PLACA, PLACA_MERCOSUL
}