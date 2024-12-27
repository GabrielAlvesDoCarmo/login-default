package br.com.gds.login.feature.login.repository_firebase

interface LoginRepository {
    fun login()
    fun isOnline()
    fun saveDataUserLogin()
    fun logout()
}