package br.com.gds.login.feature.register.personal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.usecase.RegisterPersonalUseCase
import br.com.gds.login.feature.register.personal.usecase.RegisterUseCaseState
import br.com.gds.login.feature.register.personal.model.RegisterPersonalFormState
import kotlinx.coroutines.launch

class RegisterPersonalViewModel(
    private val useCase: RegisterPersonalUseCase
) : ViewModel() {
    private val _formState: MutableLiveData<RegisterPersonalFormState> = MutableLiveData()
    val registerPersonalFormState: LiveData<RegisterPersonalFormState> = _formState

    private var _uiState: MutableLiveData<RegisterPersonalState> = MutableLiveData()
    val uiState: LiveData<RegisterPersonalState> = _uiState

    init {
        _formState.value = RegisterPersonalFormState()
    }

    fun register(registerPersonalUser: RegisterPersonalUser) {
        _uiState.value = RegisterPersonalState.Loading
        viewModelScope.launch {
            when (val stateResult = useCase.register(registerPersonalUser)) {
                is RegisterUseCaseState.Success -> successRegister()
                is RegisterUseCaseState.Error -> errorRegister(stateResult)
            }
        }
    }

    private fun errorRegister(stateResult: RegisterUseCaseState.Error) {
        _uiState.value = RegisterPersonalState.Error(stateResult.message)
    }

    private fun successRegister() {
        _uiState.value = RegisterPersonalState.Success
    }

    fun onNameChanged(name: String) {
        _formState.value = _formState.value?.copy(
            nameState = useCase.validateName(name)
        )
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

    fun onConfirmPasswordChanged(password: String, confirmPassword: String) {
        _formState.value = _formState.value?.copy(
            confirmPasswordState = useCase.validateConfirmPassword(
                password = password,
                confirmPassword = confirmPassword
            )
        )
    }
}