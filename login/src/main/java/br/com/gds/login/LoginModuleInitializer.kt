package br.com.gds.login

import android.content.Context
import br.com.gds.login.ui.login.view.LoginFragment

object LoginModuleInitializer {

    data class Builder (
        val context: Context,
        val loginModuleDependency: LoginModuleDependency
    ){

        fun buildLogin(): LoginFragment {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return LoginModuleRouter.goToLogin()
        }



    }

}