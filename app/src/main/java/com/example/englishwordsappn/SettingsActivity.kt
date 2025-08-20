package com.example.englishwordsappn

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.englishwordsappn.databinding.ActivitySettingsBinding
import com.example.englishwordsappn.databinding.ActivityStatisticsBinding

class SettingsActivity : AppCompatActivity() {
    private var _binding: ActivitySettingsBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        returnToStartScreen()
    }

    private fun returnToStartScreen() {
        binding.ibTurnOff.setOnClickListener {
            var intent = android.content.Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}