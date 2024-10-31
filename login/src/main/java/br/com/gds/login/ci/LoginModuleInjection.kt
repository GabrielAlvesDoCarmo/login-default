package br.com.gds.login.ci

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
}

private val useCases = module {
    factory<RegisterPersonalUseCase> {
        RegisterPersonalUseCaseImpl(
            repository = get()
        )
    }
}

private val viewModels = module {
    viewModel {
        RegisterPersonalViewModel(
            useCase = get()
        )
    }
}
val appModules = listOf(viewModels, useCases, authRepository, firebaseInstance  )