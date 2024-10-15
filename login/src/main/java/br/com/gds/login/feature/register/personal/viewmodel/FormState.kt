package br.com.gds.login.feature.register.personal.viewmodel

import br.com.gds.login.utils.extensions.edittext.EditTextState

data class FormState(
    val nameState: EditTextState = EditTextState.Empty,
    val emailState: EditTextState =EditTextState.Empty,
    val passwordState: EditTextState = EditTextState.Empty,
    val confirmPasswordState: EditTextState = EditTextState.Empty
)
fun FormState.isFormValid(): Boolean {
    return nameState is EditTextState.Valid &&
            emailState is EditTextState.Valid &&
            passwordState is EditTextState.Valid &&
            confirmPasswordState is EditTextState.Valid
}