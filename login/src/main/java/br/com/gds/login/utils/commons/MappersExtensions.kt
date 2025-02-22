package br.com.gds.login.utils.commons

import br.com.gds.core.login_module.model.login.LoginResponse
import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.register.RegisterResponse
import br.com.gds.login.feature.login.model.UserLogin
import br.com.gds.login.feature.login.model.UserLoginRequest
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.repository.auth.model.UserAuthInfo
import com.google.firebase.auth.AuthResult

fun RegisterPersonalUser.toRegisterRequest() = RegisterRequest(
    email = this.email, password = this.password
)

fun AuthResult.toRegisterDomain() =  RegisterResponse(
    success = true,
    additionalInfo = this.getUserInfoResponse()
)

fun AuthResult.toLoginDomain() = LoginResponse(
    success = true,
    additionalInfo = this.getUserInfoResponse()
)

private fun AuthResult.getUserInfoResponse() = UserAuthInfo(
    isNewUser = this.additionalUserInfo?.isNewUser ?: false,
    displayName = this.user?.displayName.orEmpty(),
    email = this.user?.email.orEmpty(),
    uid = this.user?.uid.orEmpty(),
    isAnonymous = this.user?.isAnonymous ?: false,
    isEmailVerified = this.user?.isEmailVerified ?: false,
    phoneNumber = this.user?.phoneNumber.orEmpty(),
)


fun UserLogin.toLoginRequest() = UserLoginRequest(
    email = this.email, password = this.password , isRemember = this.isRemember
)