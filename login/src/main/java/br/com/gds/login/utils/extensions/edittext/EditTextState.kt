package br.com.gds.login.utils.extensions.edittext

sealed interface EditTextState {
    data object Valid : EditTextState
    data class Invalid(val errorMessage: String) : EditTextState
    data object Empty : EditTextState
}