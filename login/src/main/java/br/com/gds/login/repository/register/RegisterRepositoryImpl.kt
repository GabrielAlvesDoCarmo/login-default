package br.com.gds.login.repository.register

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

class RegisterRepositoryImpl internal constructor(
    private val auth : FirebaseAuth,
    private val realtime : FirebaseDatabase,
    private val firestore : FirebaseFirestore,
    private val storage : FirebaseStorage
): RegisterRepository {

    override suspend fun register(
        registerRequest: RegisterRequest
    ) = requestCall {
        auth.createUserWithEmailAndPassword(
            registerRequest.email, registerRequest.password
        ).await()
    }.let { requestState ->
        return@let when (requestState) {
            is RequestState.Success -> successAuthRegister(requestState.response)
            is RequestState.Error -> errorAuthRegister(requestState.message)
        }
    }

    private suspend fun successAuthRegister(
        authResult: AuthResult
    ): AuthRepositoryState {
        val registerResponse = authResult.toRegisterDomain()
        saveRealtime(registerResponse)
        return AuthRepositoryState.Success(
            data = authResult.toRegisterDomain()
        )
    }

    private fun errorAuthRegister(
        message: String
    ) = AuthRepositoryState.Error(
        message = message
    )

    private suspend fun saveRealtime(registerResponse: RegisterResponse) {
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        val value = hashMapOf(
            "isOnline" to true,
        )
        val requestCall = requestCall {
            realtime.reference.child(
                 authUser.uid
            ).setValue(value).await()
        }
        when (requestCall) {
            is RequestState.Error -> errorSaveRealtime(requestCall.message)
            is RequestState.Success -> successSaveRealtime(
                registerResponse = registerResponse,
                response = requestCall.response
            )
        }
    }

    private suspend fun successSaveRealtime(registerResponse: RegisterResponse, response: Void?) {
        saveDataRegisterFireStore(registerResponse)
    }

    private fun errorSaveRealtime(message: String) {

    }

    suspend fun saveDataRegisterFireStore(registerResponse: RegisterResponse) {
        val authUser = registerResponse.additionalInfo as UserAuthInfo

        val requestCall = requestCall {
            firestore
                .collection(authUser.uid)
                .document("teste ${authUser.displayName}")
                .set(authUser).await()
        }
        when (requestCall) {
            is RequestState.Error -> errorSaveDataRegisterFireStore(requestCall.message)
            is RequestState.Success -> successSaveDataRegisterFireStore(registerResponse,requestCall.response)
        }
    }

    private suspend fun successSaveDataRegisterFireStore(
        registerResponse: RegisterResponse,
        response: Void?
    ) {
        saveImageRegisterStorage(registerResponse)
    }

    private fun errorSaveDataRegisterFireStore(message: String) {
        TODO("Not yet implemented")
    }

    suspend fun saveImageRegisterStorage(registerResponse: RegisterResponse) {
        val authUser = registerResponse.additionalInfo as UserAuthInfo

        val requestCall = requestCall {
            storage.reference.child("teste").putFile(authUser.photoUrl).await()
        }
        when (requestCall) {
            is RequestState.Error -> errorSaveImageRegisterStorage(requestCall.message)
            is RequestState.Success -> successSaveImageRegisterStorage(requestCall.response)
        }
    }

    private fun successSaveImageRegisterStorage(response: UploadTask.TaskSnapshot?) {
        response?.storage?.downloadUrl
    }

    private fun errorSaveImageRegisterStorage(message: String) {
        TODO("Not yet implemented")
    }

}