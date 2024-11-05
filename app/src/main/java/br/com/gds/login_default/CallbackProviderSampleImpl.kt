package br.com.gds.login_default

import android.content.Context
import br.com.gds.login.provider.LoginModuleCallbackProvider

class CallbackProviderSampleImpl : LoginModuleCallbackProvider {
    override fun gotToLoginSuccess(context: Context) {
        //not implemented
    }

    override fun gotToRegisterSuccess(context: Context) {
        //not implemented
    }

}