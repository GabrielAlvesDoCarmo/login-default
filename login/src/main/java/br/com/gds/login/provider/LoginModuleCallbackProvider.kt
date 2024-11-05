package br.com.gds.login.provider


interface LoginModuleCallbackProvider {
    fun gotToLoginSuccess()
    fun gotToRegisterSuccess()
}