package br.com.gds.login.feature.login.model

data class UserLoginRequest(
    val email: String,
    val password: String,
    val isRemember: Boolean,
)
