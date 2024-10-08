package br.com.gds.login.ci

import br.com.gds.login.repository.auth.AuthRepository
import br.com.gds.login.repository.auth.AuthRepositoryImpl
import org.koin.dsl.module

private val authRepository = module {
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
}

val appModules = listOf(authRepository)