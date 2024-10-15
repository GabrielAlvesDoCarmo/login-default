package br.com.gds.login

import android.content.Context
import androidx.navigation.NavController
import br.com.gds.login.feature.register.personal.view.RegisterPersonalFragment

object LoginModuleInitializer {
    data class Builder (
        val context: Context,
        val navController: NavController,
        val loginModuleDependency: LoginModuleDependency
    ){
        fun buildLogin() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return LoginModuleRouter(navController).navigateGlobalToLogin()
        }

        fun buildRegister(): RegisterPersonalFragment {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return RegisterPersonalFragment.newInstance()
        }
// nao foi criado pois nao ser acessado fora do fluxo
//        fun buildResetPassword(): ResetPasswordFragment {
//            LoginModuleSession.loginModuleDependency = loginModuleDependency
//            return ResetPasswordFragment.newInstance()
//        }
    }
}