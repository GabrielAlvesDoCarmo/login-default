package br.com.gds.login.feature.container.model

import android.os.Parcelable
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LayoutDefault(
    val defaultColors: DefaultColors = DefaultColors(),
    val fullScreen: Boolean = false,
    val isStatusBarEnabled: Boolean = true,
    val isNavigationBarEnabled: Boolean = true,
) : Parcelable