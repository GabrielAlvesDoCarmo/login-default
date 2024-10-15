package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.login.repository.auth.state.AuthRepositoryState

interface RegisterPersonalRepository {
    suspend fun register(
        registerRequest: RegisterRequest
    ) : AuthRepositoryState
}