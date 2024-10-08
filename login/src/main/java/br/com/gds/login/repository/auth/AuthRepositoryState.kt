package br.com.gds.login.repository.auth

sealed interface AuthRepositoryState {
    data class Success<T>(val data: T) : AuthRepositoryState
    data class Error(val message: String) : AuthRepositoryState
}