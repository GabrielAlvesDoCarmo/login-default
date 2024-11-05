package br.com.gds.login.provider

import android.content.Context


interface LoginModuleCallbackProvider {
    fun gotToLoginSuccess(context: Context)
    fun gotToRegisterSuccess(context: Context)
}