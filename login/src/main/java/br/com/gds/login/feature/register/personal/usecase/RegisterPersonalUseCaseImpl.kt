package br.com.gds.login.feature.register.personal.usecase

import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.repository_firebase.RegisterPersonalRepository
import br.com.gds.login.feature.register.personal.repository_firebase.RegisterPersonalState
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_CONFIRM_PASSWORD
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_NAME
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_PASSWORD
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_NAME
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_PASSWORD
import br.com.gds.login.utils.commons.toRegisterRequest
import br.com.gds.login.utils.extensions.edittext.EditTextState

class RegisterPersonalUseCaseImpl(
    private val repository: RegisterPersonalRepository
) : RegisterPersonalUseCase {
    override suspend fun register(registerPersonalUser: RegisterPersonalUser): RegisterUseCaseState {
        val requestState = repository.register(registerRequest = registerPersonalUser.toRegisterRequest())
        return when(requestState){
            is RegisterPersonalState.Error -> RegisterUseCaseState.Error(
                message = requestState.message
            )
            is RegisterPersonalState.Success -> RegisterUseCaseState.Success
        }
    }

    override fun validateName(name: String): EditTextState {
        Regex(REGEX_NAME).matches(name).apply {
            return if (!this) EditTextState.Invalid(
                errorMessage = ERROR_MESSAGE_NAME
            )
            else EditTextState.Valid
        }
    }

    override fun validateEmail(email: String): EditTextState {
        Regex(REGEX_EMAIL).apply {
            return if (!matches(email)) return EditTextState.Invalid(
                errorMessage = ERROR_MESSAGE_EMAIL
            ) else EditTextState.Valid
        }
    }

    override fun validatePassword(password: String): EditTextState {
        Regex(REGEX_PASSWORD).apply {
            return if (!matches(password)) return EditTextState.Invalid(
                errorMessage = ERROR_MESSAGE_PASSWORD
            ) else EditTextState.Valid
        }
    }

    override fun validateConfirmPassword(password: String, confirmPassword: String): EditTextState {
        return if (
            validatePassword(password) is EditTextState.Invalid
            || validatePassword(confirmPassword) is EditTextState.Invalid
            || password != confirmPassword
        ) EditTextState.Invalid(
            errorMessage = ERROR_MESSAGE_CONFIRM_PASSWORD
        ) else EditTextState.Valid
    }
}