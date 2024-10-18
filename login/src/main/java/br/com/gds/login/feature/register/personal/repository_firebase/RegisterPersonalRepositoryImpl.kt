package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.core.login_module.commons.network.requestCall
import br.com.gds.core.login_module.commons.network.state.RequestState
import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.register.RegisterResponse
import br.com.gds.login.feature.register.personal.model.RegisterPersonalResponse
import br.com.gds.login.repository.auth.model.UserAuthInfo
import br.com.gds.login.utils.commons.LoginModuleConstants.EMPTY_SPACE
import br.com.gds.login.utils.commons.LoginModuleConstants.Repositories.Register.KEY_REALTIME_DATA_USER
import br.com.gds.login.utils.commons.LoginModuleConstants.Repositories.Register.KEY_REALTIME_EMAIL
import br.com.gds.login.utils.commons.LoginModuleConstants.Repositories.Register.KEY_REALTIME_IS_ONLINE
import br.com.gds.login.utils.commons.LoginModuleConstants.Repositories.Register.KEY_REALTIME_NAME
import br.com.gds.login.utils.commons.LoginModuleConstants.UNDERSCORE
import br.com.gds.login.utils.commons.toRegisterDomain
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal class RegisterPersonalRepositoryImpl internal constructor(
    private val auth: FirebaseAuth,
    private val realtime: FirebaseDatabase,
    private val firestore: FirebaseFirestore,
) : RegisterPersonalRepository {

    override suspend fun register(
        registerRequest: RegisterRequest
    ) = requestCall {
        auth.createUserWithEmailAndPassword(
            registerRequest.email, registerRequest.password
        ).await()

    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> saveStatusOnline(
                registerResponse = requestState.response.toRegisterDomain()
            )

            is RequestState.Error -> RegisterPersonalState.Error(
                message = requestState.message
            )
        }
    }

    override suspend fun saveStatusOnline(
        registerResponse: RegisterResponse
    ): RegisterPersonalState {
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        val value = hashMapOf(
            KEY_REALTIME_IS_ONLINE to true,
            KEY_REALTIME_NAME to authUser.displayName,
            KEY_REALTIME_EMAIL to authUser.email
        )
        val requestCall = requestCall {
            realtime.reference.child(
                authUser.uid
            ).setValue(value).await()
        }
        return when (requestCall) {
            is RequestState.Error -> RegisterPersonalState.Error(requestCall.message)
            is RequestState.Success -> saveDataUser(registerResponse)
        }
    }

    override suspend fun saveDataUser(
        registerResponse: RegisterResponse
    ): RegisterPersonalState {
        val authUser = registerResponse.additionalInfo as UserAuthInfo

        val requestCall = requestCall {
            firestore
                .collection(authUser.uid)
                .document(
                    "$KEY_REALTIME_DATA_USER${
                        authUser.displayName.replace(
                            EMPTY_SPACE,
                            UNDERSCORE
                        )
                    }"
                )
                .set(authUser).await()
        }
        return when (requestCall) {
            is RequestState.Error -> RegisterPersonalState.Error(requestCall.message)
            is RequestState.Success -> successSaveDataRegisterFireStore(
                registerResponse,
            )
        }
    }

    private fun successSaveDataRegisterFireStore(
        registerResponse: RegisterResponse,
    ): RegisterPersonalState {
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        return RegisterPersonalState.Success(
            RegisterPersonalResponse(
                uid = authUser.uid
            )
        )
    }
}