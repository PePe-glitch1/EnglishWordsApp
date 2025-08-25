package com.example.englishwordsappn

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.englishwordsappn.data.Prefs

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.init(this)
        AppCompatDelegate.setDefaultNightMode(
            if (Prefs.isDark()) AppCompatDelegate.MODE_NIGHT_NO
            else AppCompatDelegate.MODE_NIGHT_YES
        )
    }
}
