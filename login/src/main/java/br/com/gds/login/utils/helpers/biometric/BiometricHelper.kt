package br.com.gds.login.utils.helpers.biometric

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.gds.login.utils.extensions.toastMessage

class BiometricHelper(
    private val fragment: Fragment,
    private val callback: LoginBiometricCallback
) {
    fun showBiometric() {
        val (msg, result) = isBiometricAvailable(fragment.requireContext(), true)
        if (result) biometricPrompt().authenticate(getBiometricPromptInfo())
        else fragment.toastMessage(msg)
    }

    private val executor = ContextCompat.getMainExecutor(fragment.requireContext())

    private val authCallback = object : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            callback.onBiometricError()

        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            callback.onBiometricFaled()

        }

        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            callback.onBiometricSuccess()
        }
    }

    private fun biometricPrompt() = BiometricPrompt(
        fragment, executor, authCallback
    )


    private fun getBiometricPromptInfo() = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Autenticação biométrica")
        .setSubtitle("Faça login usando sua impressão digital")
        .setDescription("Confirme sua identidade para acessar o aplicativo.")
        .setConfirmationRequired(true)
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
        .build()

    private fun isBiometricAvailable(
        context: Context,
        isValidateStrong: Boolean = false
    ): Pair<String, Boolean> {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return "Versao nao suporta" to false
        }
        val type =
            if (isValidateStrong) BiometricManager.Authenticators.BIOMETRIC_STRONG else BiometricManager.Authenticators.BIOMETRIC_WEAK
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(type)) {
            BiometricManager.BIOMETRIC_SUCCESS -> "Sucesso" to true
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> "Nao disponivel no aparelho" to false
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> "Mau Funcionamento " to false
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> "Digital nao cadastrada" to false
            else -> "Erro desconhecido" to false
        }
    }
}