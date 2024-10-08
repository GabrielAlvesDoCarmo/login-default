package br.com.gds.login.ui.register.viewmodel

import br.com.gds.login.ui.register.model.UserRegister

data class RegisterState(
    private val isLoading: Boolean = false,
    private val error: String? = null,
    private val success: UserRegister? = null,
    ) {
    fun isLoading() = isLoading && error == null && success == null
    fun isSuccess() = success != null && error == null && !isLoading

    fun setSuccess(user: UserRegister) = this.copy(success = user)
    fun setError(error: String) = this.copy(error = error)
    fun setLoading(loading: Boolean) = this.copy(isLoading = loading)
    fun clear() = this.copy(error = null, success = null)

}
