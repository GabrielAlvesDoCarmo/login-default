package br.com.gds.login

import android.content.Context
import br.com.gds.login.feature.container.action.NavigationScreenAction
import br.com.gds.login.feature.container.view.MainLoginContainerActivity

object LoginModuleInitializer {
    data class Builder(
        val context: Context,
        val loginModuleDependency: LoginModuleDependency
    ) {

        init {
            LoginModuleSession.loginModuleDependency = loginModuleDependency
        }

        fun initModuleLogin(
            action: NavigationScreenAction = NavigationScreenAction.ToLogin
        ) = MainLoginContainerActivity.newInstance(
            context, action
        ).also(context::startActivity)
    }
}