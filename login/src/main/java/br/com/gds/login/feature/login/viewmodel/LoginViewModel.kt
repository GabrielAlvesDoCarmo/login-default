package br.com.gds.login.feature.login.viewmodel

import androidx.lifecycle.ViewModel
import br.com.gds.login.feature.login.model.UserLogin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
