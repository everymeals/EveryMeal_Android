package com.everymeal.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

object DataStoreKey {
    val UNIVERSITY_INDEX = stringPreferencesKey("univ_index")
    val UNIVERSITY_NAME = stringPreferencesKey("univ_name")
}

class LocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalDataSource {
    override suspend fun saveUniversity(index: Int, univName: String) {
        dataStore.edit {
            it[DataStoreKey.UNIVERSITY_INDEX] = index.toString()
            it[DataStoreKey.UNIVERSITY_NAME] = univName
        }
    }

    override suspend fun getUniversityIndex(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { prefs ->
                prefs[DataStoreKey.UNIVERSITY_INDEX].orEmpty()
            }
    }
}