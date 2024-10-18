package br.com.gds.login.feature.register.personal.model

import androidx.annotation.ColorRes
import java.io.Serializable

data class RegisterPersonalUI(
    val enableButtonAddress: Boolean = false,
    val enableNickname: Boolean = false,
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
) : Serializable
