package br.com.gds.login.ui.register.viewmodel

import androidx.lifecycle.ViewModel
import br.com.gds.login.ui.register.model.UserRegister
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    fun register() {

    }

    fun setErrorMessage(message: String) {
        _uiState.value = _uiState.value.setError(message)
    }

    fun clearErrorMessage() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun setSuccessState() {
        _uiState.value = _uiState.value.setSuccess(user = getUserRegister())
    }

    fun setLoadingState() {
        _uiState.value = _uiState.value.setLoading(loading = true)
    }

    fun clearSuccessState() {
        _uiState.value = _uiState.value.copy(success = null)
    }

    fun clearLoadingState() {
        _uiState.value = _uiState.value.copy(isLoading = false)
    }


    private fun getUserRegister(): UserRegister = UserRegister(
        name = "",
        email = "",
        password = "",
        confirmPassword = ""
    )


}