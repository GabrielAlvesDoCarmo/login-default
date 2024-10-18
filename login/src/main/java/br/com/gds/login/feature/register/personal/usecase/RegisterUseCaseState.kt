package br.com.gds.login.feature.register.personal.usecase

sealed interface RegisterUseCaseState {
    data object Success : RegisterUseCaseState
    data class Error(val message: String) : RegisterUseCaseState

}