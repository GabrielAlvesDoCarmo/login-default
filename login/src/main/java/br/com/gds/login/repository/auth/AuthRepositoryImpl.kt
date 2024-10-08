package br.com.gds.login.repository.auth

import android.app.Activity
import android.net.wifi.MloLink
import br.com.gds.core.login_module.commons.network.requestCall
import br.com.gds.core.login_module.commons.network.state.RequestState
import br.com.gds.core.login_module.model.login.LoginRequest
import br.com.gds.core.login_module.model.login.LoginResponse
import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.resetpassword.ResetPasswordRequest
import br.com.gds.login.utils.extensions.toDomain
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeInfo
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FederatedAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {
    private val auth by lazy { FirebaseAuth.getInstance() }

//    val rererere5 = requestState.response.user.updatePhoneNumber()
//    val rererere269 = requestState.response.user.delete()

    override suspend fun login(
        loginRequest: LoginRequest
    ) = requestCall {
        auth.signInWithEmailAndPassword(
            loginRequest.email, loginRequest.password
        ).await()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> successLogin(requestState.response)
            is RequestState.Error -> errorLogin(requestState.message)
        }
    }


    override suspend fun register(
        registerRequest: RegisterRequest
    ) = requestCall {
        auth.createUserWithEmailAndPassword(
            registerRequest.email, registerRequest.password
        ).await()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> successRegister(requestState.response)
            is RequestState.Error -> errorRegister(requestState.message)
        }
    }


    override suspend fun logout() = requestCall {
        auth.signOut()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> successLogout()
            is RequestState.Error -> errorLogout(requestState.message)
        }
    }


    override suspend fun resetPassword(
        resetRequest: ResetPasswordRequest
    ) = requestCall {
        auth.sendPasswordResetEmail(resetRequest.email).await()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> successResetPassword()
            is RequestState.Error -> errorResetPassword(requestState.message)
        }
    }

    override suspend fun verifyPasswordResetCode(
        code: String
    ): AuthRepositoryState =
        requestCall {
            auth.verifyPasswordResetCode(code).await()
        }.let { requestState ->
            when (requestState) {
                is RequestState.Success -> successVerifyPasswordResetCode(requestState.response)
                is RequestState.Error -> errorVerifyPasswordResetCode(requestState.message)
            }
        }

    override suspend fun confirmPasswordReset(
        code: String,
        newPassword: String
    ): AuthRepositoryState =
        requestCall {
            auth.confirmPasswordReset(code, newPassword).await()
        }.let { requestState ->
            when (requestState) {
                is RequestState.Success -> successConfirmPasswordReset()
                is RequestState.Error -> errorConfirmPasswordReset(requestState.message)
            }
        }

    override suspend fun applyActionCode(code: String): AuthRepositoryState =
        requestCall {
            auth.applyActionCode(code).await()
        }.let { requestState ->
            when (requestState) {
                is RequestState.Success -> successApplyActionCode()
                is RequestState.Error -> errorApplyActionCode(requestState.message)
            }
        }

    override suspend fun loginAnonymous() = requestCall {
        auth.signInAnonymously()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> successLoginAnonymous(requestState.response)
            is RequestState.Error -> errorLoginAnonymous(requestState.message)
        }
    }

    override suspend fun recapcha() = requestCall {
        auth.initializeRecaptchaConfig()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Error -> errorReCaptcha(requestState.message)
            is RequestState.Success -> successReCaptcha(requestState.response)
        }
    }

    override suspend fun startActivityProvider(
        activity: Activity,
        federatedAuthProvider: FederatedAuthProvider
    ) = requestCall {
        auth.startActivityForSignInWithProvider(
            activity, federatedAuthProvider
        )
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Error -> errorStartActivityProvider(requestState.message)
            is RequestState.Success -> successStartActivityProvider()
        }
    }

    override suspend fun validateCredentialProvider(
        authCredential: AuthCredential
    ) = requestCall {
        auth.signInWithCredential(authCredential)
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Error -> errorValidateCredentialProvider(requestState.message)
            is RequestState.Success -> successValidateCredentialProvider(requestState.response)

        }
    }

    override suspend fun updateFirebaseUser(
        userFirebase: FirebaseUser
    ) = requestCall {
        auth.updateCurrentUser(userFirebase)
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Error -> errorUpdateFirebaseUser(requestState.message)
            is RequestState.Success -> successUpdateFirebaseUser()
        }
    }

    override suspend fun loginWithLink(
        email: String,
        link: String
    ) = requestCall {
        auth.signInWithEmailLink(email, link)
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Error -> errorLoginWithLink(requestState.message)
            is RequestState.Success -> successLoginWithLink(requestState.response)
        }
    }

    private fun successStartActivityProvider(): AuthRepositoryState {
//        validateCredentialProvider()
        TODO("Not yet implemented")
    }

    private fun errorStartActivityProvider(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun successValidateCredentialProvider(response: Task<AuthResult>): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorValidateCredentialProvider(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun successUpdateFirebaseUser(): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorUpdateFirebaseUser(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }


    private fun successReCaptcha(response: Task<Void>): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorReCaptcha(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun successLoginWithLink(response: Task<AuthResult>): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorLoginWithLink(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun successLoginAnonymous(response: Task<AuthResult>): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorLoginAnonymous(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun successApplyActionCode(): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorApplyActionCode(message: String): AuthRepositoryState {
        TODO("Not yet implemented")

    }

    private fun successConfirmPasswordReset(): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorConfirmPasswordReset(message: String): AuthRepositoryState {
        TODO("Not yet implemented")

    }

    private fun successVerifyPasswordResetCode(response: String?): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorVerifyPasswordResetCode(message: String): AuthRepositoryState {
        TODO("Not yet implemented")
    }


    private fun successResetPassword(): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorResetPassword(
        message: String
    ): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun successLogout(): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorLogout(
        message: String
    ): AuthRepositoryState {
        TODO("Not yet implemented")
    }


    private fun successRegister(
        requestState: AuthResult
    ): AuthRepositoryState {
        TODO("Not yet implemented")
    }

    private fun errorRegister(
        message: String
    ): AuthRepositoryState {
        TODO("Not yet implemented")

    }

    private fun successLogin(
        requestState: AuthResult
    ) = AuthRepositoryState.Success(
        getLoginResponse(requestState)
    )

    private fun errorLogin(
        message: String
    ) = AuthRepositoryState.Error(
        message = message
    )

    private fun getLoginResponse(
        authResult: AuthResult
    ) = LoginResponse(
        success = true,
        additionalInfo = authResult.user.toDomain(
            authResult.additionalUserInfo?.isNewUser
        )
    )
}