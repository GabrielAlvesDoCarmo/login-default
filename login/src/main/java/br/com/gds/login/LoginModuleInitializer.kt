package br.com.gds.login

import android.content.Context
import androidx.navigation.NavController
import br.com.gds.login.feature.container.action.NavigationScreenAction

object LoginModuleInitializer {
    data class Builder (
        val context: Context,
        val navController: NavController,
        val loginModuleDependency: LoginModuleDependency
    ){
        private val router = LoginModuleRouter(navController)

        fun initModuleLogin(action: NavigationScreenAction = NavigationScreenAction.ToLogin){
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return router.initModuleLogin(context,action)
        }

        fun buildLogin() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return router.navigateGlobalToLogin()
        }

        fun buildRegister() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return router.navigateGlobalToRegister()
        }

        fun buildAddressRegister() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return router.navigateGlobalAddressRegister()
        }

        fun buildAutomovelRegister() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            return router.navigateGlobalAutomovelRegister()
        }

// nao foi criado pois nao ser acessado fora do fluxo
//        fun buildResetPassword(): ResetPasswordFragment {
//            LoginModuleSession.loginModuleDependency = loginModuleDependency
//            return ResetPasswordFragment.newInstance()
//        }
    }
}