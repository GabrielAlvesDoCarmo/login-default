package br.com.gds.login.feature.register.address.model

import androidx.annotation.ColorRes
import java.io.Serializable

data class AddressRegisterUI(
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
) : Serializable