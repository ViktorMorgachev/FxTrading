package data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.learning.lessons.utils.utils.Logger
import data.PreferencesKeys.DEVICE_ID
import data.PreferencesKeys.INTRO_WAS_PASSED
import data.PreferencesKeys.PRE_TEST_PASSED
import data.PreferencesKeys.USER_ID
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

object PreferencesKeys {
    val PRE_TEST_PASSED = booleanPreferencesKey("pre_test_passed")
    val INTRO_WAS_PASSED = booleanPreferencesKey("intro_was_passed")
    val USER_ID = longPreferencesKey("user_id")
    val DEVICE_ID = stringPreferencesKey("devices_id")
}

@Singleton
class DataStoreHelper @Inject constructor(val context: Context) {


    val Context.dataPreferences: DataStore<Preferences> by preferencesDataStore(
        name = "fx_trading_data_store"
    )

    suspend fun examWasPassed(wasPassed: Boolean) {
        context.dataPreferences.edit { preferences->
            Logger.log("DataStoreHelper", "examWasPassed $wasPassed")
            preferences[PRE_TEST_PASSED] = wasPassed
        }
    }

    suspend fun introWasPassed(wasPassed: Boolean){
        context.dataPreferences.edit { preferences->
            Logger.log("DataStoreHelper", "introWasPassed $wasPassed")
            preferences[INTRO_WAS_PASSED] = wasPassed
        }
    }

    suspend fun saveUserID(userID: Int){
        context.dataPreferences.edit { preferences->
            Logger.log("DataStoreHelper", "saveUserID $userID")
            preferences[USER_ID] = userID.toLong()
        }
    }

    suspend fun saveDeviceID(deviceID: String){
        context.dataPreferences.edit { preferences->
            Logger.log("DataStoreHelper", "saveDeviceID $deviceID")
            preferences[DEVICE_ID] = deviceID
        }
    }

    suspend fun wasPassedIntro() = context.dataPreferences.data.map { it[INTRO_WAS_PASSED] ?: false }

    suspend fun wasPassedExam() = context.dataPreferences.data.map { it[PRE_TEST_PASSED] ?: false }

    suspend fun userID() = context.dataPreferences.data.map { (it[USER_ID] ?: -1).toInt() }

}