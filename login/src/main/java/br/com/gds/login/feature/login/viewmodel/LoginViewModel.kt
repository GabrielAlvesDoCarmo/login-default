package br.com.gds.login.feature.login.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gds.login.feature.login.model.UserLogin
import br.com.gds.login.feature.login.usecase.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val useCase: LoginUseCase
) : ViewModel() {

    private val _formState: MutableLiveData<LoginFormState> = MutableLiveData()
    val loginFormState: LiveData<LoginFormState> = _formState

    private var _uiState: MutableLiveData<LoginUIState> = MutableLiveData()
    val uiState: LiveData<LoginUIState> = _uiState

    private val _verificationId = MutableLiveData<String>()
    val verificationId: LiveData<String> = _verificationId


    init {
        _formState.value = LoginFormState()
    }

    fun verifyPhoneNumber(phoneNumber: String, activity: Activity) {
        viewModelScope.launch {
            useCase.sendCodeLoginPhone(phoneNumber, activity)
        }
    }


    fun rememberDataUser() {

    }

    fun removeDataUser() {

    }

    suspend fun login(userLogin: UserLogin) {
        _uiState.value = useCase.login(userLogin)
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
