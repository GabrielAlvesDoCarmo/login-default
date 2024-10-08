package br.com.gds.login.utils.extensions.edittext

import android.content.res.ColorStateList
import androidx.annotation.StyleRes
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val SIMPLE_REGEX = "[^a-zA-Z0-9@._\\-+]"
private const val EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$"
private const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
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
            !text.contains(Regex(LOWERCASE_REGEX)) -> state.value = EditTextState.Invalid("A senha deve conter pelo menos uma letra minuscula")
            !text.contains(Regex(UPPERCASE_REGEX)) -> state.value = EditTextState.Invalid("A senha deve conter pelo menos uma letra maiuscula")
            !text.contains(Regex(NUMBER_REGEX)) -> state.value = EditTextState.Invalid("A senha deve conter pelo menos um numero")
            !text.contains(Regex(SPECIAL_CHAR_REGEX)) -> state.value = EditTextState.Invalid("A senha deve conter pelo menos um caracter especial")
            text.length <= 7 -> state.value = EditTextState.Invalid("A senha deve conter no minimo 8 caracteres")
            text.length >= 20 -> state.value = EditTextState.Invalid("A senha deve conter no maximo 20 caracteres")
//            !text.contains(Regex(PASSWORD_REGEX)) -> state.value = EditTextState.Invalid("A senha deve conter pelo menos uma letra maiuscula, uma letra minuscula, um numero e um caracter especial")
            else -> state.value = EditTextState.Valid

        }
    }
    return state.asStateFlow()
}

fun TextInputLayout.applyStyle(@StyleRes styleRes: Int) {
    val context = this.context
    val typedArray = context.obtainStyledAttributes(styleRes, com.google.android.material.R.styleable.TextInputLayout)

    val boxStrokeColor = typedArray.getColor(com.google.android.material.R.styleable.TextInputLayout_boxStrokeColor, 0)
    val startIconTint = typedArray.getColor(com.google.android.material.R.styleable.TextInputLayout_startIconTint, 0)
    val endIconTint = typedArray.getColor(com.google.android.material.R.styleable.TextInputLayout_endIconTint, 0)

    this.boxStrokeColor = boxStrokeColor
    this.setStartIconTintList(ColorStateList.valueOf(startIconTint))
    this.setEndIconTintList(ColorStateList.valueOf(endIconTint))

    typedArray.recycle()
}

inline fun <reified T> MutableLiveData<T>.toLiveData(): LiveData<T> {
    return this as LiveData<T>
}