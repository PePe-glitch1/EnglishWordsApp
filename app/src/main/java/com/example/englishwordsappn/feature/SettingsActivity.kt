package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationManagerCompat
import com.example.englishwordsappn.feature.StartScreenActivity
import com.example.englishwordsappn.databinding.ActivitySettingsBinding
import com.example.englishwordsappn.data.Prefs

class SettingsActivity : AppCompatActivity() {
    private var _binding: ActivitySettingsBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvTeamOnOff.text=
            if (Prefs.isDark()) getString(com.example.englishwordsappn.R.string.text_light)
            else getString(com.example.englishwordsappn.R.string.text_dark)

        returnToStartScreen()

        val notification = Prefs.isNotificationON()
        val team = Prefs.isDark()
        val learnLanguage = Prefs.getLearningLanguage()

        onOffNotification(notification)
        onOffTeam(team)
        setLearningLanguage(learnLanguage)

    }

    private fun onOffNotification(notification: Boolean) {
    }

    private fun onOffTeam(team: Boolean) {
        binding.clSettings3.setOnClickListener {
            if (team) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Prefs.setDark(false)
                binding.tvTeamOnOff.text =
                    getString(com.example.englishwordsappn.R.string.text_dark)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Prefs.setDark(true)
                binding.tvTeamOnOff.text =
                    getString(com.example.englishwordsappn.R.string.text_light)
            }
            recreate()
        }
    }

    private fun setLearningLanguage(learnLanguage: String) {
    }


    private fun returnToStartScreen() {
        binding.ibTurnOff.setOnClickListener {
            var intent = Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}