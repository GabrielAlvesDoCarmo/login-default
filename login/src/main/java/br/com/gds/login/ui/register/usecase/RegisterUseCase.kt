package br.com.gds.login.ui.register.usecase

import br.com.gds.login.ui.register.model.UserRegister
import br.com.gds.login.utils.extensions.edittext.EditTextState

interface RegisterUseCase {
    suspend fun register(userRegister: UserRegister): Boolean
    fun validateName(name: String) : EditTextState
    fun validateEmail(email: String): EditTextState
    fun validatePassword(password: String): EditTextState
    fun validateConfirmPassword(password: String, confirmPassword: String): EditTextState
    fun validateImage(email: String): EditTextState
}