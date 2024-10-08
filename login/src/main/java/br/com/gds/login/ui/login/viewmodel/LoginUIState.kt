package br.com.gds.login.ui.login.viewmodel

import br.com.gds.login.ui.login.model.UserLogin

data class LoginUIState(
    val isLoading: Boolean = false,
    val user: UserLogin? = null,
    val error: String? = null
) {
    fun isSuccessful() = user != null && error == null && !isLoading
    fun setLoading(isLoading: Boolean) = copy(isLoading = isLoading)
    fun setError(error: String) = copy(error = error)
    fun setSuccess(user: UserLogin) = copy(user = user)
}
