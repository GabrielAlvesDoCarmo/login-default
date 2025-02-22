package br.com.gds.login.di

import br.com.gds.login.feature.login.repository_firebase.LoginRepository
import br.com.gds.login.feature.login.repository_firebase.LoginRepositoryImpl
import br.com.gds.login.feature.login.usecase.LoginUseCase
import br.com.gds.login.feature.login.usecase.LoginUseCaseImpl
import br.com.gds.login.feature.login.viewmodel.LoginViewModel
import br.com.gds.login.repository.auth.AuthRepository
import br.com.gds.login.repository.auth.AuthRepositoryImpl
import br.com.gds.login.feature.register.personal.repository_firebase.RegisterPersonalRepository
import br.com.gds.login.feature.register.personal.repository_firebase.RegisterPersonalRepositoryImpl
import br.com.gds.login.feature.register.personal.usecase.RegisterPersonalUseCase
import br.com.gds.login.feature.register.personal.usecase.RegisterPersonalUseCaseImpl
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val firebaseInstance = module {
    single {
        FirebaseAuth.getInstance()
    }
    single {
        FirebaseStorage.getInstance()
    }
    single {
        FirebaseFirestore.getInstance()
    }
    single {
        FirebaseDatabase.getInstance()
    }
}

private val authRepository = module {
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single<RegisterPersonalRepository> {
        RegisterPersonalRepositoryImpl(
            auth = get(),
            realtime = get(),
            firestore = get(),
        )
    }
    single<LoginRepository> {
        LoginRepositoryImpl(
            auth = get(),
            realtime = get(),
            firestore =get()
        )
    }
}

private val useCases = module {
    factory<RegisterPersonalUseCase> {
        RegisterPersonalUseCaseImpl(
            repository = get()
        )
    }

    factory<LoginUseCase> {
        LoginUseCaseImpl(
            repository =  get()
        )
    }
}

private val viewModels = module {
    viewModel {
        RegisterPersonalViewModel(
            useCase = get()
        )
    }

    viewModel {
        LoginViewModel(
            useCase = get()
        )
    }
}
val loginModulesInjection = listOf(viewModels, useCases, authRepository, firebaseInstance  )