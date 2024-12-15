package br.com.gds.login.feature.login.usecase

import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.ERROR_MESSAGE_PASSWORD
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.UseCases.Register.REGEX_PASSWORD
import br.com.gds.login.utils.extensions.edittext.EditTextState

class LoginUseCaseImpl : LoginUseCase{

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
}