package br.com.gds.login.feature.login.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginUI(
    @ColorRes val backgroundColor: Int = R.color.white,
    @ColorRes val titleColor: Int = R.color.black
) : Parcelable