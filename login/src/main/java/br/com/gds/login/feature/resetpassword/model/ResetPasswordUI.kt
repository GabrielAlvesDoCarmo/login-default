package br.com.gds.login.feature.resetpassword.model

import androidx.annotation.ColorRes
import java.io.Serializable

data class ResetPasswordUI(
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
): Serializable