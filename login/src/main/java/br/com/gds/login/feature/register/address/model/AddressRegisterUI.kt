package br.com.gds.login.feature.register.address.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressRegisterUI(
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
) : Parcelable