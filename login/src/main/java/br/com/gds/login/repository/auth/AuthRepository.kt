package br.com.gds.login.repository.auth

import android.app.Activity
import br.com.gds.core.login_module.model.login.LoginRequest
import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.resetpassword.ResetPasswordRequest
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FederatedAuthProvider
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): AuthRepositoryState
    suspend fun register(registerRequest: RegisterRequest): AuthRepositoryState
    suspend fun logout(): AuthRepositoryState
    suspend fun resetPassword(resetRequest: ResetPasswordRequest): AuthRepositoryState
    suspend fun verifyPasswordResetCode(code: String): AuthRepositoryState
    suspend fun confirmPasswordReset(code: String, newPassword: String): AuthRepositoryState
    suspend fun applyActionCode(code: String): AuthRepositoryState
    suspend fun loginAnonymous(): AuthRepositoryState
    suspend fun recapcha(): AuthRepositoryState
    suspend fun startActivityProvider(activity: Activity, federatedAuthProvider: FederatedAuthProvider): AuthRepositoryState
    suspend fun validateCredentialProvider(authCredential: AuthCredential): AuthRepositoryState
    suspend fun updateFirebaseUser(userFirebase: FirebaseUser): AuthRepositoryState
    suspend fun loginWithLink(email: String, link: String): AuthRepositoryState
}