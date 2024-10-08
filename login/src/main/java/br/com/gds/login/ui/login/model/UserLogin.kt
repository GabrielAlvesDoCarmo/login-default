package br.com.gds.login.ui.login.model

import br.com.gds.core.login_module.commons.interfaces.User

data class UserLogin(
    override val email: String,
    val password: String,
    val isRemember: Boolean,
):User
