package br.com.gds.login.feature.container.model

import android.os.Parcelable
import androidx.annotation.ColorRes
import br.com.gds.login.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class LettersColors(
    @ColorRes val default: Int = android.R.color.black,
    @ColorRes val hint: Int = android.R.color.darker_gray,
    @ColorRes val link: Int = R.color.test_1,
): Parcelable