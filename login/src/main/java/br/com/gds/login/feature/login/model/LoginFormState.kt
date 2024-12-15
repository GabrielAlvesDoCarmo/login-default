package br.com.gds.login.feature.login.model

import br.com.gds.login.utils.extensions.edittext.EditTextState

data class LoginFormState(
    val emailState: EditTextState = EditTextState.Empty,
    val passwordState: EditTextState = EditTextState.Empty,
)
fun LoginFormState.isFormValid(): Boolean {
    return emailState is EditTextState.Valid &&
            passwordState is EditTextState.Valid
}