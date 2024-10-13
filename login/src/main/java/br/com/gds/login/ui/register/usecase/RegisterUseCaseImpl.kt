package br.com.gds.login.ui.register.usecase

import br.com.gds.login.ui.register.model.UserRegister
import br.com.gds.login.utils.extensions.edittext.EditTextState

class RegisterUseCaseImpl : RegisterUseCase {
    override suspend fun register(userRegister: UserRegister): Boolean {
        TODO("Not yet implemented")
    }

    override fun validateName(name: String): EditTextState {
        Regex("^[a-zA-Z]+$").matches(name).apply {
            return if (!this) EditTextState.Invalid(
                errorMessage = "Nome inválido. Use apenas letras."
            )
            else EditTextState.Valid
        }
    }

    override fun validateEmail(email: String): EditTextState {
        Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").apply {
            return if (!matches(email)) return EditTextState.Invalid(
                errorMessage = "Email inválido."
            ) else EditTextState.Valid
        }
    }

    override fun validatePassword(password: String): EditTextState {
        Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$").apply {
            return if (!matches(password)) return EditTextState.Invalid(
                errorMessage = "Senha inválida. A senha deve ter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
            ) else EditTextState.Valid
        }
    }

    override fun validateConfirmPassword(password: String, confirmPassword: String): EditTextState {
       return if (password != confirmPassword) EditTextState.Invalid(
            errorMessage = "As senhas não coincidem."
        ) else EditTextState.Valid
    }

    override fun validateImage(email: String): EditTextState {
        TODO("Not yet implemented")
    }
}