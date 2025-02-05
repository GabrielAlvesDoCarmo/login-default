package br.com.gds.login.feature.container.action

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class NavigationScreenAction : Parcelable {
    data object ToLogin : NavigationScreenAction()
    data object ToRegisterPerson : NavigationScreenAction()
    data object ToRegisterAddress : NavigationScreenAction()
    data object ToResetPassword : NavigationScreenAction()
}