package br.com.gds.login

import br.com.gds.login.provider.LoginModuleCallbackProvider
import br.com.gds.login.utils.commons.LayoutSetup

data class LoginModuleDependency(
    var layoutSetup: LayoutSetup = LayoutSetup(),
    val loginModuleCallbackProvider: LoginModuleCallbackProvider? = null
)
