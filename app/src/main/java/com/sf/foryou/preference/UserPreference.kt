package com.sf.foryou.preference

import android.content.Context
import com.sf.foryou.model.UserModel

/**
 * بِسْمِ اللهِ الرَّحْمٰنِ الرَّحِيْمِ
 * Created By Fahmi on 18/10/20
 */

class UserPreference(context: Context) {
    companion object {
        const val PREFERENCE_NAME = "preference_name"
        const val USER_NAME = "user_name"
        const val IS_OPEN_ON_BOARDING = "is_open_on_boarding"
    }

    private val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val model = UserModel()

    fun setUserPreferenceData(value: UserModel) {
        val editor = preference.edit()
        editor.putString(USER_NAME, value.userName)
        editor.apply()
    }

    fun getString() : UserModel {
        model.userName = preference.getString(USER_NAME, "") ?: ""
        return model
    }
}