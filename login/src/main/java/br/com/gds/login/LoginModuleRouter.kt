package br.com.gds.login

import androidx.navigation.NavController
import br.com.gds.login.feature.login.view.LoginFragmentDirections
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI

//object LoginModuleRouter {
//    fun goToLogin() = LoginFragment.newInstance()
//    fun goToRegister() = RegisterFragment.newInstance()
//    fun goToResetPassword() = ResetPasswordFragment.newInstance()
//
//}

class LoginModuleRouter(
    private val navController: NavController
) {

    fun navigateGlobalToLogin() {
        navController.navigate(
            directions = LoginFragmentDirections.actionGlobalLoginFragment()
        )
    }

    fun navigateLoginToRegister(registerPersonalUI: RegisterPersonalUI) {
        navController.navigate(
            directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment(
                registerUI = registerPersonalUI
            )
        )
    }

    fun navigateLoginToResetPassword() {
        navController.navigate(
            directions = LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment2()
        )
    }
}