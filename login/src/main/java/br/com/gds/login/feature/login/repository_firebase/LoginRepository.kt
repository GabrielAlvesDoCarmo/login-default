package br.com.gds.login.feature.login.repository_firebase

import android.app.Activity
import br.com.gds.core.login_module.model.login.LoginResponse
import br.com.gds.login.feature.login.model.UserLoginRequest
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

interface LoginRepository {
    suspend fun login(loginRequest: UserLoginRequest) : LoginResponse
    suspend fun isOnline()
    suspend fun saveDataUserLogin()
    suspend fun logout()

    suspend fun sendVerificationCode(
        phoneNumber: String,
        activity: Activity,
    )
    suspend fun signInWithCredential(
        credential: PhoneAuthCredential
    ): LoginResponse
}