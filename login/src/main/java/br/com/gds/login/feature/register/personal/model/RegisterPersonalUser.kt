package br.com.gds.login.feature.register.personal.model

import br.com.gds.core.login_module.commons.interfaces.User

data class RegisterPersonalUser(
    val name: String,
    override val email: String,
    val password: String,
    val confirmPassword: String,
) : User