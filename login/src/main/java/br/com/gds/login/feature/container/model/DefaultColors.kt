package br.com.gds.login.feature.container.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class DefaultColors(
    @ColorRes val primaryColor: Int = R.color.white,
    @ColorRes val secondaryColor: Int = R.color.white,
    @ColorRes val tertiaryColor: Int = R.color.white,
    val lettersColors: LettersColors = LettersColors()
) : Parcelable