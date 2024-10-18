package br.com.gds.login.feature.register.personal.viewmodel
//
//data class RegisterPersonalState(
//    private val isLoading: Boolean = false,
//    private val error: String? = null,
//    private val isSuccess: Boolean = false
//    ) {
//    fun isLoading() = !isSuccess && isLoading && error == null
//    fun isSuccess() = error == null && !isLoading && isSuccess
//    fun isError() = error != null && !isLoading && !isSuccess
//
//    fun setError(error: String?) = this.copy(error = error, isLoading = false)
//    fun setLoading(loading: Boolean) = this.copy(isLoading = loading, error = null)
//}

sealed class RegisterPersonalState {
    data object Loading : RegisterPersonalState()
    data object Success : RegisterPersonalState()
    data class Error(val message: String) : RegisterPersonalState()

}