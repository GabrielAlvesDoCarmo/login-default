package br.com.gds.login.ui.login.viewmodel

sealed interface EditTextState {
    data object Valid : EditTextState
    data class Invalid(val errorMessage: String) : EditTextState
    data object Empty : EditTextState
}