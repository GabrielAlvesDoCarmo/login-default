package br.com.gds.login.feature.login.repository_firebase

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

sealed interface LoginRepositoryState {
    sealed interface PhoneAuth : LoginRepositoryState {
        data class VerificationCompleted(val credential: PhoneAuthCredential) : PhoneAuth
        data class VerificationFailed(val exception: FirebaseException) : PhoneAuth
        data class CodeAutoRetrievalTimeOut(val verificationId: String) : PhoneAuth
        data class CodeSent(
            val verificationId: String,
            val token: PhoneAuthProvider.ForceResendingToken
        ) : PhoneAuth
    }
}