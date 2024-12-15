package br.com.gds.login.feature.register.personal.model

import br.com.gds.login.utils.extensions.edittext.EditTextState

data class RegisterPersonalFormState(
    val nameState: EditTextState = EditTextState.Empty,
    val emailState: EditTextState =EditTextState.Empty,
    val passwordState: EditTextState = EditTextState.Empty,
    val confirmPasswordState: EditTextState = EditTextState.Empty
)
fun RegisterPersonalFormState.isFormValid(): Boolean {
    return nameState is EditTextState.Valid &&
            emailState is EditTextState.Valid &&
            passwordState is EditTextState.Valid &&
            confirmPasswordState is EditTextState.Valid
}