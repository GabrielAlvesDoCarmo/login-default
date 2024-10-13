package br.com.gds.login.repository.register

import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.login.repository.auth.state.AuthRepositoryState

interface RegisterRepository {
    suspend fun register(
        registerRequest: RegisterRequest
    ) : AuthRepositoryState
}