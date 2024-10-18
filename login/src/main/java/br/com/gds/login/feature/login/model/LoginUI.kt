package br.com.gds.login.feature.login.model

import androidx.annotation.ColorRes
import java.io.Serializable

data class LoginUI(
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
) : Serializable