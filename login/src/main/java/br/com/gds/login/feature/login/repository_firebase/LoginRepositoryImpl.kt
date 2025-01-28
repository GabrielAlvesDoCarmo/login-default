package br.com.gds.login.feature.login.repository_firebase

import android.app.Activity
import br.com.gds.core.login_module.commons.network.requestCall
import br.com.gds.core.login_module.commons.network.state.RequestState
import br.com.gds.core.login_module.model.login.LoginResponse
import br.com.gds.login.feature.login.model.UserLoginRequest
import br.com.gds.login.utils.commons.toLoginDomain
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

internal class LoginRepositoryImpl(
    private val auth: FirebaseAuth,
    private val realtime: FirebaseDatabase,
    private val firestore: FirebaseFirestore,
) : LoginRepository {

    private fun getCallbacks(): Pair<LoginRepositoryState.PhoneAuth?, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
        var stateValue: LoginRepositoryState.PhoneAuth? = null
        val callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                stateValue = LoginRepositoryState.PhoneAuth.VerificationCompleted(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                stateValue = LoginRepositoryState.PhoneAuth.VerificationFailed(p0)
            }

            override fun onCodeAutoRetrievalTimeOut(p0: String) {
                super.onCodeAutoRetrievalTimeOut(p0)
                stateValue = LoginRepositoryState.PhoneAuth.CodeAutoRetrievalTimeOut(p0)
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(p0, p1)
                stateValue = LoginRepositoryState.PhoneAuth.CodeSent(p0, p1)
            }
        }
        return stateValue to callback
    }

    override suspend fun login(
        loginRequest: UserLoginRequest
    ): LoginResponse = requestCall {
        auth.signInWithEmailAndPassword(
            loginRequest.email,
            loginRequest.password
        ).await()
    }.let { requestState ->
        return@let when(requestState){
            is RequestState.Success -> requestState.response.toLoginDomain()
            is RequestState.Error -> throw Exception(requestState.message)
        }
    }

    override suspend fun isOnline() {

    }

    override suspend fun saveDataUserLogin() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
       return requestCall {
           auth.signOut()
       }.let {
           return@let when(it){
               is RequestState.Success -> Unit
               is RequestState.Error -> throw Exception(it.message)
           }
       }
    }

    override suspend fun sendVerificationCode(
        phoneNumber: String,
        activity: Activity,
    ) {
        val (state, callback) = getCallbacks()
        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callback)
                .build()
        )
        when(state){
            is LoginRepositoryState.PhoneAuth.VerificationCompleted -> {
                signInWithCredential(state.credential)
            }
            is LoginRepositoryState.PhoneAuth.CodeSent -> {
//                Salvar o verificationId para usar posteriormente na verificação do código.
//                (Opcional) Salvar o token para reenviar o código de verificação, se necessário.
//                Navegar para a tela onde o
                state.verificationId

//                Navegar para a tela onde o usuário irá inserir o código de verificação.
                state.token
            }
            is LoginRepositoryState.PhoneAuth.CodeAutoRetrievalTimeOut -> {
//                Salvar o verificationId para usar posteriormente na verificação do código.
//                Permitir que o usuário insira o código de verificação manualmente.
                state.verificationId
            }
            is LoginRepositoryState.PhoneAuth.VerificationFailed -> {


            }
            else -> TODO()
        }
    }

    override suspend fun signInWithCredential(
        credential: PhoneAuthCredential
    ): LoginResponse {
        return requestCall {
            auth.signInWithCredential(credential).await()
        }.let { requestState ->
            return@let when (requestState) {
                is RequestState.Success -> requestState.response.toLoginDomain()
                is RequestState.Error -> throw Exception(requestState.message)
            }
        }
    }
}