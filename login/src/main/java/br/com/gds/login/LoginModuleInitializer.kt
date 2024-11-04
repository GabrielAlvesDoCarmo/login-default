package br.com.gds.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

object LoginModuleInitializer {
    data class Builder (
        val context: Context,
        val loginModuleDependency: LoginModuleDependency
    ){
//        private val router = LoginModuleRouter(navController)

        fun buildLogin() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            val navController = Navigation.findNavController(context as AppCompatActivity,R.id.login_graph)
            val router = LoginModuleRouter(navController)
            return router.navigateGlobalToLogin()
        }

        fun buildRegister() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            val navController = Navigation.findNavController(context as AppCompatActivity,R.id.login_graph)
            val router = LoginModuleRouter(navController)
            return router.navigateGlobalToRegister()
        }

        fun buildAddressRegister() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            val navController = Navigation.findNavController(context as AppCompatActivity,R.id.login_graph)
            val router = LoginModuleRouter(navController)
            return router.navigateGlobalAddressRegister()
        }

        fun buildAutomovelRegister() {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
            val navController = Navigation.findNavController(context as AppCompatActivity,R.id.login_graph)
            val router = LoginModuleRouter(navController)
            return router.navigateGlobalAutomovelRegister()
        }

// nao foi criado pois nao ser acessado fora do fluxo
//        fun buildResetPassword(): ResetPasswordFragment {
//            LoginModuleSession.loginModuleDependency = loginModuleDependency
//            return ResetPasswordFragment.newInstance()
//        }
    }
}