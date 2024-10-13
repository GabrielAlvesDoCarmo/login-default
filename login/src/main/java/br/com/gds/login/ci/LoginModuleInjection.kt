package br.com.gds.login.ci

import br.com.gds.login.repository.auth.AuthRepository
import br.com.gds.login.repository.auth.AuthRepositoryImpl
import br.com.gds.login.ui.register.usecase.RegisterUseCase
import br.com.gds.login.ui.register.usecase.RegisterUseCaseImpl
import br.com.gds.login.ui.register.viewmodel.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val authRepository = module {
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
}

private val useCases = module {
    factory<RegisterUseCase> {
        RegisterUseCaseImpl()
    }
}

private val viewModels = module {
    viewModel {
        RegisterViewModel(
            useCase = get()

        )
    }
}
val appModules = listOf(viewModels, useCases, authRepository)