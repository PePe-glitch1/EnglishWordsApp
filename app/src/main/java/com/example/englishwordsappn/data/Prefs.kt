package com.example.englishwordsappn.data

object Prefs {
    private lateinit var sp: android.content.SharedPreferences

    fun init(context: android.content.Context) {
        sp = context.getSharedPreferences("settings", android.content.Context.MODE_PRIVATE)
    }

    private const val KEY_NOTIFICATION = "notification"
    private const val KEY_IS_DARK = "is_dark"
    private const val KEY_LEARN_LANGUAGE = "learn_language"


    fun setNotificationON(value: Boolean) {
        sp.edit().putBoolean(KEY_NOTIFICATION, value).apply()
    }

    fun setDark(v: Boolean){
        sp.edit().putBoolean(KEY_IS_DARK, v).apply()
    }


    fun setLanguage(value: String) {
        sp.edit().putString(KEY_LEARN_LANGUAGE, value).apply()
    }

    fun isNotificationON(): Boolean =
        sp.getBoolean(KEY_NOTIFICATION, false)

    fun isDark(): Boolean =
        sp.getBoolean(KEY_IS_DARK, false)

    fun getLanguage(default: String = "default"): String =
        sp.getString(KEY_LEARN_LANGUAGE, default) ?: default

}