package br.com.gds.login.feature.login.usecase

import android.app.Activity
import br.com.gds.login.feature.login.model.UserLogin
import br.com.gds.login.feature.login.repository_firebase.LoginRepository
import br.com.gds.login.feature.login.viewmodel.LoginUIState
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_PASSWORD
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_PASSWORD
import br.com.gds.login.utils.commons.toLoginRequest
import br.com.gds.login.utils.extensions.edittext.EditTextState
import com.google.firebase.auth.PhoneAuthProvider

class LoginUseCaseImpl(
    private val repository: LoginRepository
) : LoginUseCase{

    override fun validateEmail(email: String): EditTextState {
        if (email.isEmpty()) return EditTextState.Empty
        Regex(REGEX_EMAIL).apply {
            return if (!matches(email)) return EditTextState.Invalid(
                errorMessage = ERROR_MESSAGE_EMAIL
            ) else EditTextState.Valid
        }
    }


    override fun validatePassword(password: String): EditTextState {
        if (password.isEmpty()) return EditTextState.Empty
        Regex(REGEX_PASSWORD).apply {
            return if (!matches(password)) return EditTextState.Invalid(
                errorMessage = ERROR_MESSAGE_PASSWORD
            ) else EditTextState.Valid
        }
    }

    override suspend fun sendCodeLoginPhone(
        phoneNumber: String,
        activity: Activity,
    ) {
        repository.sendVerificationCode(phoneNumber, activity)
    }

    override suspend fun login(userLogin: UserLogin): LoginUIState {
//        repository.login(userLogin.toLoginRequest())
        return LoginUIState.Loading
    }
}