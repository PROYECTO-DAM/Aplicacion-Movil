package com.example.proyectodam.Network.DataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.proyectodam.Classes.Usuario
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferencesService @Inject constructor(private val dataStore: DataStore<Preferences>): UserPreferences {
    override suspend fun addUser(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        dataStore.edit { preferences -> preferences[preferenceKey] = value }
    }

    override suspend fun getUser(key: String): Usuario? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = dataStore.data.first()
            Gson().fromJson(preferences[preferenceKey], Usuario::class.java)
        } catch (e:Exception) {
            null
        }
    }
}