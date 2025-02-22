package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.register.RegisterResponse

interface RegisterPersonalRepository {
    suspend fun register(
        registerRequest: RegisterRequest
    ) : RegisterPersonalState

    suspend fun saveUserListOnline(
        registerResponse: RegisterResponse
    ): Boolean

    suspend fun saveUserListOffline(
        registerResponse: RegisterResponse
    ): Boolean

    suspend fun saveDataUser(
        registerResponse: RegisterResponse
    ): RegisterPersonalState
}