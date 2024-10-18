package br.com.gds.login

import androidx.navigation.NavController
import br.com.gds.login.feature.login.view.LoginFragmentDirections
import br.com.gds.login.feature.register.address.view.AddressRegisterFragmentDirections
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.register.personal.view.RegisterPersonalFragmentDirections

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
            directions = LoginGraphDirections.actionGlobalLoginFragment()
        )
    }

    fun navigateGlobalToRegister() {
        navController.navigate(
            directions = LoginGraphDirections.actionGlobalRegisterFragment()
        )
    }

    fun navigateGlobalAddressRegister() {
        navController.navigate(
            directions = LoginGraphDirections.actionGlobalAddressRegisterFragment()
        )
    }

    fun navigateGlobalAutomovelRegister() {
        navController.navigate(
            directions = LoginGraphDirections.actionGlobalAutomovelRegisterFragment()
        )
    }

    fun navigateLoginToRegister() {
        navController.navigate(
            directions = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        )
    }

    fun navigateLoginToResetPassword() {
        navController.navigate(
            directions = LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment2()
        )
    }

    fun navigateRegisterToAddressRegister(){
        navController.navigate(
            directions = RegisterPersonalFragmentDirections.actionRegisterToAddressRegister()
        )
    }

    fun navigateRegisterToAutomovelRegister(){
        navController.navigate(
            directions = RegisterPersonalFragmentDirections.actionRegisterToAutomovelRegister()
        )
    }

    fun navigateAddressRegisterToAutomovelRegister(){
        navController.navigate(
            directions = AddressRegisterFragmentDirections.actionAddressRegisterToAutomovelRegister()
        )
    }
}