package br.com.gds.login.utils.commons

sealed class UIState<T: Any?> {
    data object Loading : UIState<Nothing>()
    data class Success<T>(val data: T? = null) : UIState<T>()
    data class Error(val message: String, val value: Any? = null) : UIState<Nothing>()
}