package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.login.feature.register.personal.model.RegisterPersonalResponse

sealed interface RegisterPersonalState {
    data class Success(
        val response : RegisterPersonalResponse
    ) : RegisterPersonalState

    data class Error(
        val message: String
    ) : RegisterPersonalState
}