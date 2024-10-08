package br.com.gds.login.utils.extensions

import android.net.Uri
import br.com.gds.core.login_module.model.login.LoginResponse
import br.com.gds.login.repository.auth.UserAuthInfo
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser?.toDomain(isNewUser: Boolean? ): UserAuthInfo {
    return UserAuthInfo(
        isNewUser = isNewUser ?: false,
        displayName = this?.displayName ?: "",
        email = this?.email ?: "",
        uid = this?.uid ?: "",
        isAnonymous = this?.isAnonymous ?: false,
        isEmailVerified = this?.isEmailVerified ?: false,
        phoneNumber = this?.phoneNumber ?: "",
        photoUrl = this?.photoUrl ?: Uri.EMPTY
    )
}