package br.com.gds.login.feature.register.personal.viewmodel

sealed class RegisterPersonalState {
    data object Loading : RegisterPersonalState()
    data object Success : RegisterPersonalState()
    data class Error(val message: String) : RegisterPersonalState()
}