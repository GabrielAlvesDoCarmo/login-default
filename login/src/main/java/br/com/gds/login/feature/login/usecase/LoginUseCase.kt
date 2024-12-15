package br.com.gds.login.feature.login.usecase

import br.com.gds.login.utils.extensions.edittext.EditTextState

interface LoginUseCase {
    fun validateEmail(email: String): EditTextState
    fun validatePassword(password: String): EditTextState
}