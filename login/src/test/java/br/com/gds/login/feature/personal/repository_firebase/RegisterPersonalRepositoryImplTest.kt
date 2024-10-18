package br.com.gds.login.feature.personal.repository_firebase

import br.com.gds.login.feature.register.personal.repository_firebase.RegisterPersonalRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterPersonalRepositoryImplTest {

    private val auth: FirebaseAuth = mockk()
    private val realtime: FirebaseDatabase = mockk()
    private val firestore: FirebaseFirestore = mockk()

    @RelaxedMockK private lateinit var repository : RegisterPersonalRepositoryImpl

    @Before
    fun setUp() {
        repository = RegisterPersonalRepositoryImpl(auth, realtime, firestore)
    }

    @Test
    fun register() {
    }

    @Test
    fun saveStatusOnline() {
    }

    @Test
    fun saveDataUser() {
    }
}