package br.com.gds.login.feature.container.model

import android.os.Parcelable
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginLayoutDefault(
    val statusBarColor: Int = R.color.white,
    val navigationBarColor: Int = R.color.white,
) : Parcelable