package br.com.gds.login.ui.register.model

import br.com.gds.core.login_module.commons.interfaces.User

data class UserRegister(
    val immage: String,
    val name: String,
    override val email: String,
    val password: String,
    val confirmPassword: String,
) : User