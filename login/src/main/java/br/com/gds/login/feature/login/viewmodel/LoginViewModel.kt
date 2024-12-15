package br.com.gds.login.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.gds.login.feature.login.model.LoginFormState
import br.com.gds.login.feature.login.model.UserLogin
import br.com.gds.login.feature.login.usecase.LoginUseCase

class LoginViewModel(
    private val useCase: LoginUseCase
) : ViewModel() {

    private val _formState: MutableLiveData<LoginFormState> = MutableLiveData()
    val loginFormState: LiveData<LoginFormState> = _formState

    private var _uiState: MutableLiveData<LoginUIState> = MutableLiveData()
    val uiState: LiveData<LoginUIState> = _uiState

    init {
        _formState.value = LoginFormState()
    }


    fun rememberDataUser() {

    }

    fun removeDataUser() {

    }

    fun login(userLogin: UserLogin) {
    }

    fun onEmailChanged(email: String) {
        _formState.value = _formState.value?.copy(
            emailState = useCase.validateEmail(email)
        )
    }

    fun onPasswordChanged(password: String) {
        _formState.value = _formState.value?.copy(
            passwordState = useCase.validatePassword(password)
        )
    }


}
