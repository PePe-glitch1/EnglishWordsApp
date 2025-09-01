package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.englishwordsappn.R
import com.example.englishwordsappn.data.Prefs
import com.example.englishwordsappn.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private var _binding: ActivitySettingsBinding? = null
    private val binding get() = _binding ?: error("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvTeamOnOff.text = if (Prefs.isDark()) {
            getString(R.string.text_light)
        } else {
            getString(R.string.text_dark)
        }

        val notification = Prefs.isNotificationON()
        val team = Prefs.isDark()
        val language = Prefs.getLanguage()

        onOffNotification(notification)
        onOffTeam(team)
        setLanguage(language)
        returnToStartScreen()
    }

    private fun onOffNotification(notification: Boolean) {

        binding.clSettings1.setOnClickListener {
            // TODO: включение/выключение уведомлений
        }
    }

    private fun onOffTeam(team: Boolean) {
        binding.clSettings3.setOnClickListener {
            val isDark = Prefs.isDark()
            if (isDark) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Prefs.setDark(false)
                binding.tvTeamOnOff.text = getString(R.string.text_light)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Prefs.setDark(true)
                binding.tvTeamOnOff.text = getString(R.string.text_dark)
            }
            recreate()
        }
    }

    private fun setLanguage(language: String) {
        binding.clSettings2.setOnClickListener {
            if(language == "uk"){
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.getEmptyLocaleList())
                Prefs.setLanguage("default")
            } else if(language == "default"){
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("uk"))
                Prefs.setLanguage("uk")
            }
        }
    }

    private fun returnToStartScreen() {
        binding.ibTurnOff.setOnClickListener {
            startActivity(Intent(this, StartScreenActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
