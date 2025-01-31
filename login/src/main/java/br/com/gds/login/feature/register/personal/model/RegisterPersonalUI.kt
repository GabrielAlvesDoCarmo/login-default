package br.com.gds.login.feature.register.personal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterPersonalUI(
    val enableButtonAddress: Boolean = false,
    val enableRegisterProviders: Boolean = false,
) : Parcelable
