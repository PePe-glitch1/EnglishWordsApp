package com.example.englishwordsappn

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.englishwordsappn.databinding.ActivityStartScreenBinding
import kotlinx.coroutines.flow.combine

class StartScreenActivity : AppCompatActivity() {

    private var _binding: ActivityStartScreenBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        goToMenuActivity()
    }

    private fun goToMenuActivity() {
        binding.btStartLearn.setOnClickListener {
            val intent = android.content.Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}