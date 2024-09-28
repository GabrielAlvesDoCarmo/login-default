package br.com.gds.login.provider

import br.com.gds.core.login_module.provider.AuthFirebaseProvider

sealed class LoginModuleProviderImpl {
    interface Login : AuthFirebaseProvider.Login<Unit>
    interface Register : AuthFirebaseProvider.Login<Unit>
    interface ResetPassword : AuthFirebaseProvider.Login<Unit>
}