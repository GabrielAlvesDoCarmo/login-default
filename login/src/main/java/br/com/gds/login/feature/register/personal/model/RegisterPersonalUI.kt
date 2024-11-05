package br.com.gds.login.feature.register.personal.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterPersonalUI(
    val enableButtonAddress: Boolean = false,
    val enableNickname: Boolean = false,
    @ColorRes val backgroundColor: Int = R.color.white,
    @ColorRes val titleColor: Int = R.color.black
) : Parcelable
