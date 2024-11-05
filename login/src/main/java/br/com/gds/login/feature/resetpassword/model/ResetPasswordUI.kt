package br.com.gds.login.feature.resetpassword.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResetPasswordUI(
    @ColorRes val backgroundColor: Int = 0,
    @ColorRes val titleColor: Int = 0
): Parcelable