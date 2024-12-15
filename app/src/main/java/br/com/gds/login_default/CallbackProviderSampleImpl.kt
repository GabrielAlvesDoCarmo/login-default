package br.com.gds.login_default

import android.util.Log
import br.com.gds.login.provider.LoginModuleCallbackProvider

class CallbackProviderSampleImpl : LoginModuleCallbackProvider {
    override fun successLogin() {
        //Not yet implemented
        println("successLogin")
    }

    override fun backButtonLogin() {
        //Not yet implemented
    }

    override fun errorLogin() {
        //Not yet implemented
    }

    override fun successRegister() {
        //Not yet implemented
    }

    override fun backButtonRegister() {
        //Not yet implemented
    }

    override fun errorRegister() {
        //Not yet implemented
    }

    override fun successResetPassword() {
        //Not yet implemented
    }

    override fun backButtonResetPassword() {
        //Not yet implemented
    }

    override fun errorResetPassword() {
        //Not yet implemented
    }

    override fun clickBtnLogin() {
        Log.i("TAG_AAA", "clickBtnLogin")
    }
}