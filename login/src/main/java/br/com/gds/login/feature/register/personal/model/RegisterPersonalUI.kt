package br.com.gds.login.feature.register.personal.model

import java.io.Serializable

data class RegisterPersonalUI(
    val enableButtonAddress: Boolean = false,
    val enableNickname: Boolean = false,
) : Serializable
