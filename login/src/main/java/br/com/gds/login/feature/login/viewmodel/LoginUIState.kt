package br.com.gds.login.feature.login.viewmodel

import br.com.gds.login.feature.login.model.UserLogin

sealed interface LoginUIState {
    data object Loading : LoginUIState
    data class Error(val message: String) : LoginUIState
    data class Success(val userLogin: UserLogin) : LoginUIState
}
