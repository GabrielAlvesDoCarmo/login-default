package br.com.gds.login.feature.login.repository_firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

internal class LoginRepositoryImpl (
    private val auth: FirebaseAuth,
    private val realtime: FirebaseDatabase,
    private val firestore: FirebaseFirestore,
) : LoginRepository {
    override fun login() {

    }

    override fun isOnline() {
        TODO("Not yet implemented")
    }

    override fun saveDataUserLogin() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}