package br.com.gds.login.utils.helpers.biometric

interface LoginBiometricCallback {
    fun onBiometricSuccess()
    fun onBiometricError()
    fun onBiometricFaled()
}