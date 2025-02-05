package br.com.gds.login.feature.register.personal.repository_firebase

import br.com.gds.core.login_module.commons.network.constants.FirebaseMsgError
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
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.util.Date

internal class RegisterPersonalRepositoryImpl internal constructor(
    private val auth: FirebaseAuth,
    private val realtime: FirebaseDatabase,
    private val firestore: FirebaseFirestore,
) : RegisterPersonalRepository {

    override suspend fun register(
        registerRequest: RegisterRequest
    ): RegisterPersonalState {
        return try {
            val response = withContext(Dispatchers.IO) {
                auth.createUserWithEmailAndPassword(
                    registerRequest.email, registerRequest.password
                ).await()
            }
            saveUserListOnline(response.toRegisterDomain())
            saveDataUser(response.toRegisterDomain())
        } catch (exception: Exception) {
            return when (exception) {
                is FirebaseAuthWeakPasswordException -> RegisterPersonalState.Error(
                    message = FirebaseMsgError.Auth.WEAK_PASSWORD
                )

                is FirebaseAuthEmailException -> RegisterPersonalState.Error(
                    message = FirebaseMsgError.Auth.INVALID_EMAIL
                )

                is FirebaseAuthInvalidUserException -> RegisterPersonalState.Error(
                    message = FirebaseMsgError.Auth.INVALID_USER
                )

                is FirebaseAuthUserCollisionException -> RegisterPersonalState.Error(
                    message = FirebaseMsgError.Auth.USER_COLLISION
                )

                is FirebaseAuthRecentLoginRequiredException -> RegisterPersonalState.Error(
                    message = FirebaseMsgError.Auth.RECENT_LOGIN_REQUIRED
                )

                else -> RegisterPersonalState.Error(
                    message = FirebaseMsgError.Auth.ERROR_UNKNOWN
                )
            }
        }

    }

    override suspend fun saveUserListOnline(
        registerResponse: RegisterResponse
    ): Boolean {
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        val value = hashMapOf(authUser.uid to true)
        return try {
            withContext(Dispatchers.IO) {
                realtime.reference.child("IsOnline").setValue(value).await()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun saveUserListOffline(
        registerResponse: RegisterResponse
    ): Boolean {
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        val value = hashMapOf(authUser.uid to false)
        return try {
            withContext(Dispatchers.IO) {
                realtime.reference.child("IsOnline").setValue(value).await()
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun saveDataUser(
        registerResponse: RegisterResponse
    ): RegisterPersonalState {
        val authUser = registerResponse.additionalInfo as UserAuthInfo
        return try {
            val date = LocalDateTime.now().let { dateTimeNow->
                return@let "${dateTimeNow.dayOfMonth}-${dateTimeNow.monthValue}-${dateTimeNow.year}"
            }

            firestore.collection("Cadastro")
                .document(date)
                .collection(authUser.uid)
                .document("dados_pessoais")
                .set(authUser)
                .await()
            RegisterPersonalState.Success(RegisterPersonalResponse(""))
        }catch (e : Exception){
            e.printStackTrace()
            RegisterPersonalState.Error(e.message.toString())
        }
    }
}