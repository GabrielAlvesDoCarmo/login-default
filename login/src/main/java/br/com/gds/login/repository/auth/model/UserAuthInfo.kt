package br.com.gds.login.repository.auth.model

import android.net.Uri

data class UserAuthInfo(
    val isNewUser:Boolean,
    val displayName: String,
    val email: String,
    val uid: String,
    val isAnonymous: Boolean,
    val isEmailVerified: Boolean,
    val phoneNumber: String,
    val photoUrl: Uri
)
