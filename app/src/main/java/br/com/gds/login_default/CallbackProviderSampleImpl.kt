package br.com.gds.login_default

import android.content.Context
import android.util.Log
import android.widget.Toast
import br.com.gds.login.provider.LoginModuleCallbackProvider

class CallbackProviderSampleImpl(
    private val context: Context
) : LoginModuleCallbackProvider {
    override fun successLogin() {
        //Not yet implemented
        Toast.makeText(context, "Login realizado com sucesso", Toast.LENGTH_SHORT).show()
    }

    override fun backButtonLogin() {
        //Not yet implemented
        Toast.makeText(context, "botao voltar", Toast.LENGTH_SHORT).show()
    }

    override fun errorLogin() {
        //Not yet implemented
        Toast.makeText(context, "Erro ao realizar login", Toast.LENGTH_SHORT).show()
    }

    override fun successRegister() {
        //Not yet implemented
        Toast.makeText(context, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
    }

    override fun backButtonRegister() {
        //Not yet implemented
        Toast.makeText(context, "botao voltar", Toast.LENGTH_SHORT).show()
    }

    override fun errorRegister() {
        //Not yet implemented
        Toast.makeText(context, "Erro ao realizar cadastro", Toast.LENGTH_SHORT).show()
    }

    override fun successResetPassword() {
        //Not yet implemented
        Toast.makeText(context, "Email enviado com sucesso", Toast.LENGTH_SHORT).show()
    }

    override fun backButtonResetPassword() {
        //Not yet implemented
        Toast.makeText(context, "botao voltar", Toast.LENGTH_SHORT).show()
    }

    override fun errorResetPassword() {
        //Not yet implemented
        Toast.makeText(context, "Erro ao enviar email", Toast.LENGTH_SHORT).show()
    }

    override fun clickGuestUser() {

        //Not yet implemented
        Toast.makeText(context, "Click para visitante", Toast.LENGTH_SHORT).show()
    }
}