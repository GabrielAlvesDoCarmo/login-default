package br.com.gds.login.ci

import br.com.gds.login.repository.auth.AuthRepository
import br.com.gds.login.repository.auth.AuthRepositoryImpl
import br.com.gds.login.repository.register.RegisterRepository
import br.com.gds.login.repository.register.RegisterRepositoryImpl
import br.com.gds.login.ui.register.usecase.RegisterUseCase
import br.com.gds.login.ui.register.usecase.RegisterUseCaseImpl
import br.com.gds.login.ui.register.viewmodel.RegisterViewModel
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

    single<RegisterRepository> {
        RegisterRepositoryImpl(
            auth = get(),
            realtime = get(),
            firestore = get(),
            storage = get()
        )
    }
}

private val useCases = module {
    factory<RegisterUseCase> {
        RegisterUseCaseImpl(
            registerRepository = get()
        )
    }
}

private val viewModels = module {
    viewModel {
        RegisterViewModel(
            useCase = get()

        )
    }
}
val appModules = listOf(viewModels, useCases, authRepository, firebaseInstance  )