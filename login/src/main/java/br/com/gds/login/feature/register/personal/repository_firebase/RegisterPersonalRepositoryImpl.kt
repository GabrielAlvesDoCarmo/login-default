package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.core.login_module.commons.network.requestCall
import br.com.gds.core.login_module.commons.network.state.RequestState
import br.com.gds.core.login_module.model.register.RegisterRequest
import br.com.gds.core.login_module.model.register.RegisterResponse
import br.com.gds.login.repository.auth.model.UserAuthInfo
import br.com.gds.login.repository.auth.state.AuthRepositoryState
import br.com.gds.login.utils.commons.toRegisterDomain
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.tasks.await

class RegisterPersonalRepositoryImpl internal constructor(
    private val auth : FirebaseAuth,
    private val realtime : FirebaseDatabase,
    private val firestore : FirebaseFirestore,
): RegisterPersonalRepository {

    override suspend fun register(
        registerRequest: RegisterRequest
    ) = requestCall {
        auth.createUserWithEmailAndPassword(
            registerRequest.email, registerRequest.password
        ).await()

    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> {
                saveRealtime(
                    registerResponse = requestState.response.toRegisterDomain()
                )
            }
            is RequestState.Error -> RegisterPersonalState.Error(
                message = requestState.message
            )
        }
    }


    private suspend fun saveRealtime(registerResponse: RegisterResponse) : RegisterPersonalState{
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        val value = hashMapOf(
            "isOnline" to true,
            "name" to authUser.displayName,
            "email" to authUser.email
        )
        val requestCall = requestCall {
            realtime.reference.child(
                 authUser.uid
            ).setValue(value).await()
        }
        return when (requestCall) {
            is RequestState.Error -> errorSaveRealtime(requestCall.message)
            is RequestState.Success -> saveDataRegisterFireStore(registerResponse)
        }
    }

    private fun errorSaveRealtime(message: String) : RegisterPersonalState {
        return RegisterPersonalState.Error(message)
    }
    suspend fun saveDataRegisterFireStore(registerResponse: RegisterResponse) : RegisterPersonalState {
        val authUser = registerResponse.additionalInfo as UserAuthInfo

        val requestCall = requestCall {
            firestore
                .collection(authUser.uid)
                .document("teste ${authUser.displayName}")
                .set(authUser).await()
        }
       return when (requestCall) {
            is RequestState.Error -> errorSaveDataRegisterFireStore(requestCall.message)
            is RequestState.Success -> successSaveDataRegisterFireStore(registerResponse,requestCall.response)
        }
    }

    private fun successSaveDataRegisterFireStore(
        registerResponse: RegisterResponse,
        response: Void?
    ): RegisterPersonalState {
       return RegisterPersonalState.Error(registerResponse.message.toString())
    }

    private fun errorSaveDataRegisterFireStore(message: String) : RegisterPersonalState{
            return RegisterPersonalState.Error(message)
    }



}