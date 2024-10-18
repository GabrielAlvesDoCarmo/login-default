package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.register.RegisterResponse

interface RegisterPersonalRepository {
    suspend fun register(
        registerRequest: RegisterRequest
    ) : RegisterPersonalState

    suspend fun saveStatusOnline(
        registerResponse: RegisterResponse
    ): RegisterPersonalState

    suspend fun saveDataUser(
        registerResponse: RegisterResponse
    ): RegisterPersonalState
}