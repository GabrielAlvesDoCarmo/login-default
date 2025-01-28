package br.com.gds.login.feature.login.usecase

import android.app.Activity
import br.com.gds.login.feature.login.model.UserLogin
import br.com.gds.login.feature.login.viewmodel.LoginUIState
import br.com.gds.login.utils.extensions.edittext.EditTextState
import com.google.firebase.auth.PhoneAuthProvider

interface LoginUseCase {
    fun validateEmail(email: String): EditTextState
    fun validatePassword(password: String): EditTextState
    suspend fun sendCodeLoginPhone(
        phoneNumber: String,
        activity: Activity,
    )

    suspend fun login(userLogin: UserLogin): LoginUIState
}