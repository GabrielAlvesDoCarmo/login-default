package br.com.gds.login.utils.helpers

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesHelper(
    private val context: Context
){
    private val preferences = context.getSharedPreferences("login_shared_preferences", Context.MODE_PRIVATE)

    fun getLoginFirstAccess() = preferences.getBoolean("is_first_access", true)

    fun saveFirstAccessConclude()  = preferences.edit().putBoolean("is_first_access", false).apply()

}