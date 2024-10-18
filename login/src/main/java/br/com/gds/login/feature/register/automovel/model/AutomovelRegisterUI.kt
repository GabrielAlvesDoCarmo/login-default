package br.com.gds.login.feature.register.automovel.model

import androidx.annotation.ColorRes
import java.io.Serializable

data class AutomovelRegisterUI(
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
) : Serializable