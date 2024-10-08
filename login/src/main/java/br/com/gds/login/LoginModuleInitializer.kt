package br.com.gds.login

import android.content.Context
import br.com.gds.login.ui.login.view.LoginFragment
import br.com.gds.login.ui.register.view.RegisterFragment
import br.com.gds.login.ui.resetpassword.view.ResetPasswordFragment

object LoginModuleInitializer {
    data class Builder (
        val context: Context,
        val loginModuleDependency: LoginModuleDependency
    ){
        fun buildLogin(): LoginFragment {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return LoginModuleRouter.goToLogin()
        }

        fun buildRegister(): RegisterFragment {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return RegisterFragment.newInstance()
        }

        fun buildResetPassword(): ResetPasswordFragment {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return ResetPasswordFragment.newInstance()
        }
    }
}