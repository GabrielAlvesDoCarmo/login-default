package br.com.gds.login.feature.personal.usecase

import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.usecase.RegisterPersonalUseCase
import br.com.gds.login.utils.extensions.edittext.EditTextState
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class RegisterPersonalUseCaseImplTest {
    private lateinit var useCase: RegisterPersonalUseCase
    private lateinit var registerPersonalUser: RegisterPersonalUser

    @Before
    fun setUp() {
        registerPersonalUser = RegisterPersonalUser(
            immage = "",
            name = "",
            email = "",
            password = "",
            confirmPassword = ""
        )
    }

    @Test
    fun register() {
    }

    @Test
    fun `is name valid`() {
        val result = useCase.validateName("Gabriel")
        assertTrue(result is EditTextState.Valid)
    }

    @Test
    fun `is name empty invalid`() {
        val result = useCase.validateName("")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is name with number invalid`() {
        val result = useCase.validateName("Gabr1el")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is name with special character invalid`() {
        val result = useCase.validateName("Gabr@el")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is email valid`() {
        val result = useCase.validateEmail("james.iredell@examplepetstore.com")
        assertTrue(result is EditTextState.Valid)
    }

    @Test
    fun `is email empty invalid`() {
        val result = useCase.validateEmail("")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is email not contains @ invalid`() {
        val result = useCase.validateEmail("gabrielgmail.com")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is email not contains point invalid`() {
        val result = useCase.validateEmail("gabriel@gmailcom")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is password valid`() {
        val result = useCase.validatePassword("AbTp9!fok")
        assertTrue(result is EditTextState.Valid)
    }

    @Test
    fun `is password empty invalid`() {
        val result = useCase.validatePassword("")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is password NOT CONTAINS number invalid`() {
        val result = useCase.validatePassword("AbTp!fo")
        assertTrue(result is EditTextState.Invalid)
        }

    @Test
    fun `is password NOT CONTAINS special character invalid`() {
        val result = useCase.validatePassword("AbTp9fo")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is password NOT CONTAINS uppercase invalid`() {
        val result = useCase.validatePassword("abtp9!fok")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is password NOT CONTAINS lowercase invalid`() {
        val result = useCase.validatePassword("ABTP9!FOK")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is password less than 8 characters invalid`() {
        val result = useCase.validatePassword("AbTp9!f")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is password more than 20 characters invalid`() {
        val result = useCase.validatePassword("AbTp9!fokAbTp9!fokAbTp9!fok")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is confirm password valid`() {
        val result = useCase.validateConfirmPassword("AbTp9!fok", "AbTp9!fok")
        assertTrue(result is EditTextState.Valid)
    }

    @Test
    fun `is confirm password empty invalid`() {
        val result = useCase.validateConfirmPassword("", "")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is confirm password different invalid`() {
        val result = useCase.validateConfirmPassword("AbTp9!fok", "AbTp9!fo")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun `is confirm password less than 8 characters invalid`() {
        val result = useCase.validateConfirmPassword("AbTp9!f", "ZbTp9!f")
        assertTrue(result is EditTextState.Invalid)
    }

    @Test
    fun validateImage() {
    }
}