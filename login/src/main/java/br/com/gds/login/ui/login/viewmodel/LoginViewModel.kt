package br.com.gds.login.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import br.com.gds.login.ui.login.model.UserLogin
import br.com.gds.login.ui.login.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState= _uiState.asStateFlow()

    fun rememberDataUser() {

    }

    fun removeDataUser() {

    }

    fun login(userLogin: UserLogin) {

    }


}
