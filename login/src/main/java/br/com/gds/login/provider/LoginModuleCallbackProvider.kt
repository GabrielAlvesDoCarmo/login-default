package br.com.gds.login.provider


interface LoginModuleCallbackProvider {
    fun successLogin()
    fun backButtonLogin()
    fun errorLogin()

    fun successRegister()
    fun backButtonRegister()
    fun errorRegister()

    fun successRegisterAddress()
    fun backButtonRegisterAddress()
    fun errorRegisterAddress()

    fun successResetPassword()
    fun backButtonResetPassword()
    fun errorResetPassword()

    fun clickGuestUser()
}