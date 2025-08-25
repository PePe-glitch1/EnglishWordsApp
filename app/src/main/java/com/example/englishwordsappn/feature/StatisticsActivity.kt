package com.example.englishwordsappn.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.englishwordsappn.domain.trainer.LearningWords
import com.example.englishwordsappn.databinding.ActivityStatisticsBinding
import com.example.englishwordsappn.data.WordsRepository


class StatisticsActivity : AppCompatActivity() {
        private var _binding: ActivityStatisticsBinding? = null
        val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            _binding = ActivityStatisticsBinding.inflate(layoutInflater)
            setContentView(binding.root)

            updateStats()
            returnToStartScreen()
    }

    override fun onStart() {
        super.onStart()
        updateStats()
    }

    private fun returnToStartScreen() {
        binding.ibTurnOff.setOnClickListener {
            var intent = Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateStats() {
        binding.tvStatisticLearned.text = WordsRepository.learnedCount().toString()
        binding.tvStatisticAnLearned.text = WordsRepository.notLearnedCount().toString()
    }
}