package br.com.gds.login.feature.container.model

import android.os.Parcelable
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LayoutDefault(
    val isStatusBarEnabled: Boolean = true,
    val statusBarColor: Int = R.color.white,
    val isNavigationBarEnabled: Boolean = true,
    val navigationBarColor: Int = R.color.white,
) : Parcelable