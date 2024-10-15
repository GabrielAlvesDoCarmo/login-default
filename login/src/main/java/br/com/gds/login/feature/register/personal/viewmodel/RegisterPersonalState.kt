package br.com.gds.login.feature.register.personal.viewmodel

import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser

data class RegisterPersonalState(
    private val isLoading: Boolean = false,
    private val error: String? = null,
    private val success: RegisterPersonalUser? = null,
    ) {
    fun isLoading() = isLoading && error == null && success == null
    fun isSuccess() = success != null && error == null && !isLoading

    fun setSuccess(user: RegisterPersonalUser) = this.copy(success = user)
    fun setError(error: String) = this.copy(error = error)
    fun setLoading(loading: Boolean) = this.copy(isLoading = loading)
    fun clear() = this.copy(error = null, success = null)

}
