package br.com.gds.login.provider

sealed interface LoginModuleProvider {
    interface Login : LoginModuleProvider{
        fun success()
    }
    interface Register : LoginModuleProvider{
        fun success()
    }
    interface ResetPassword : LoginModuleProvider{
        fun success() : Unit
    }
}