package br.com.gds.login.feature.register.personal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.usecase.RegisterPersonalUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterPersonalViewModel (
    private val useCase: RegisterPersonalUseCase
) : ViewModel() {


    private val _formState = MutableStateFlow(FormState())
    val formState: StateFlow<FormState> = _formState.asStateFlow()


    private val _uiState = MutableStateFlow(RegisterPersonalState())
    val uiState: StateFlow<RegisterPersonalState> = _uiState.asStateFlow()

    fun register(registerPersonalUser: RegisterPersonalUser) {
        viewModelScope.launch {
            val res = useCase.register(registerPersonalUser)
            when(res){
                true -> println("Sucess")
                false -> println("error")
            }
        }
    }

    fun onNameChanged(name: String) {
        _formState.value = _formState.value.copy(
            nameState = useCase.validateName(name)
        )
    }

    fun onEmailChanged(email: String) {
        _formState.value = _formState.value.copy(
            emailState = useCase.validateEmail(email)
        )
    }

    fun onPasswordChanged(password: String) {
        _formState.value = _formState.value.copy(
            passwordState = useCase.validatePassword(password)
        )
    }

    fun onConfirmPasswordChanged(password: String, confirmPassword: String) {
        _formState.value = _formState.value.copy(
            confirmPasswordState = useCase.validateConfirmPassword(
                password = password,
                confirmPassword = confirmPassword
            )
        )
    }
}