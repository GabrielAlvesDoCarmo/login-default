package br.com.gds.login.feature.login.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUI(
    val enableButtonBack: Boolean = false,
    val enableButtonRememberPassword: Boolean = false,
    val enableProviders: Boolean = false,
    val enabledRegister: Boolean = false,
    val enabledFingerPrint: Boolean = false,
) : Parcelable