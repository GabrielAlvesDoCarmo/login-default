package br.com.gds.login.feature.register.personal.usecase

import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.utils.extensions.edittext.EditTextState

interface RegisterPersonalUseCase {
    suspend fun register(registerPersonalUser: RegisterPersonalUser): Boolean
    fun validateName(name: String) : EditTextState
    fun validateEmail(email: String): EditTextState
    fun validatePassword(password: String): EditTextState
    fun validateConfirmPassword(password: String, confirmPassword: String): EditTextState
    fun validateImage(email: String): EditTextState
}