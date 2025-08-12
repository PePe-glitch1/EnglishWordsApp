package com.example.englishwordsappn

import androidx.appcompat.app.AppCompatActivity
import com.example.englishwordsappn.databinding.ActivityHomeBinding
import com.example.englishwordsappn.databinding.ActivityLearnWordBinding

class StartMenuActivity: AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openLearningWordsActivity()

    }

    private fun openLearningWordsActivity() {
        binding.btStartLearn.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}