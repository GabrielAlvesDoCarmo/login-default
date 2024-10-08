package br.com.gds.login

import android.content.Context
import br.com.gds.login.ui.login.view.LoginFragment

object LoginModuleRouter {
    fun goToLogin() = LoginFragment.newInstance()

}