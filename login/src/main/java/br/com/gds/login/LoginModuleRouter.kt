package br.com.gds.login

import br.com.gds.login.ui.login.view.LoginFragment
import br.com.gds.login.ui.register.view.RegisterFragment
import br.com.gds.login.ui.resetpassword.view.ResetPasswordFragment

object LoginModuleRouter {
    fun goToLogin() = LoginFragment.newInstance()
    fun goToRegister() = RegisterFragment.newInstance()
    fun goToResetPassword() = ResetPasswordFragment.newInstance()

}