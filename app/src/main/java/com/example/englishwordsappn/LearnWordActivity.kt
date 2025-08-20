package com.example.englishwordsappn

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.englishwordsappn.databinding.ActivityLearnWordBinding


class LearnWordActivity : AppCompatActivity() {
    private var _binding: ActivityLearnWordBinding? = null
    val binding
        get() = _binding ?: throw IllegalStateException("Binding is not initialized")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        returnToStartScreen()

        val trainer = LearningWords()
        showNextQuestion(trainer)

        with(binding) {
            btFinalContinue.setOnClickListener {
                clFinalScoreboard.isVisible = false
                btSlip.isVisible = true
                markAnswerNeutral(
                    llAnswer1,
                    tvVariantValue1,
                    tvVariantNumb1,
                    clFinalScoreboard,
                    btSlip
                )
                markAnswerNeutral(
                    llAnswer2,
                    tvVariantValue2,
                    tvVariantNumb2,
                    clFinalScoreboard,
                    btSlip
                )
                markAnswerNeutral(
                    llAnswer3,
                    tvVariantValue3,
                    tvVariantNumb3,
                    clFinalScoreboard,
                    btSlip
                )
                markAnswerNeutral(
                    llAnswer4,
                    tvVariantValue4,
                    tvVariantNumb4,
                    clFinalScoreboard,
                    btSlip
                )
                showNextQuestion(trainer)
            }
            btSlip.setOnClickListener {
                showNextQuestion(trainer)
            }
        }

    }

    private fun returnToStartScreen() {
        binding.ibTurnOff.setOnClickListener {
            var intent = android.content.Intent(this, StartScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showNextQuestion(trainer: LearningWords) {
        val firstirstQuestion: Question? = trainer.getNextQuestion()
        with(binding) {
            if (firstirstQuestion == null || firstirstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionWord.isVisible = false
                layoutVariants.isVisible = false
                btSlip.text = "Complete"
            } else {
                btSlip.isVisible = true
                tvQuestionWord.isVisible = true
                tvQuestionWord.text = firstirstQuestion.correctAnswer.word

                tvVariantValue1.text = firstirstQuestion.variants[0].translation
                tvVariantValue2.text = firstirstQuestion.variants[1].translation
                tvVariantValue3.text = firstirstQuestion.variants[2].translation
                tvVariantValue4.text = firstirstQuestion.variants[3].translation

                llAnswer1.setOnClickListener {
                    if (trainer.checkAnswer(0)){
                        markAsCorrect(
                            llAnswer1,
                            tvVariantValue1,
                            tvVariantNumb1
                        )
                        showResultMessage(true)
                    } else {
                        markAsWrong(
                            llAnswer1,
                            tvVariantValue1,
                            tvVariantNumb1
                        )
                        showResultMessage(false)
                    }
                }
                llAnswer2.setOnClickListener {
                    if (trainer.checkAnswer(1)){
                        markAsCorrect(
                            llAnswer2,
                            tvVariantValue2,
                            tvVariantNumb2
                        )
                        showResultMessage(true)
                    } else {
                        markAsWrong(
                            llAnswer2,
                            tvVariantValue2,
                            tvVariantNumb2
                        )
                        showResultMessage(false)
                    }
                }
                llAnswer3.setOnClickListener {
                    if (trainer.checkAnswer(2)) {
                        markAsCorrect(
                            llAnswer3,
                            tvVariantValue3,
                            tvVariantNumb3
                        )
                        showResultMessage(true)
                    } else {
                        markAsWrong(
                            llAnswer3,
                            tvVariantValue3,
                            tvVariantNumb3
                        )
                        showResultMessage(false)
                    }
                }
                llAnswer4.setOnClickListener {
                    if (trainer.checkAnswer(3)) {
                        markAsCorrect(
                            llAnswer4,
                            tvVariantValue4,
                            tvVariantNumb4
                        )
                        showResultMessage(true)
                    } else {
                        markAsWrong(
                            llAnswer4,
                            tvVariantValue4,
                            tvVariantNumb4
                        )
                        showResultMessage(false)
                    }
                }
            }

        }
    }

    private fun LearnWordActivity.markAsWrong(
        llAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumb: TextView,
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@LearnWordActivity,
            R.drawable.shape_rounded_containers_incorrect
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@LearnWordActivity,
                R.color.falseColor
            )
        )

        tvVariantNumb.apply {
            background = ContextCompat.getDrawable(
                this@LearnWordActivity,
                R.drawable.shape_rounded_fals
            )
            setTextColor(
                ContextCompat.getColor(
                    this@LearnWordActivity,
                    R.color.white
                )
            )
        }
    }

    private fun LearnWordActivity.markAsCorrect(
        llAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumb: TextView,
    ) {
        llAnswer.background = ContextCompat.getDrawable(
            this@LearnWordActivity,
            R.drawable.shape_rounded_containers_correct
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@LearnWordActivity,
                R.color.trueColor
            )
        )

        tvVariantNumb.apply {
            background = ContextCompat.getDrawable(
                this@LearnWordActivity,
                R.drawable.shape_rounded_true
            )
            setTextColor(
                ContextCompat.getColor(
                    this@LearnWordActivity,
                    R.color.white
                )
            )
        }
    }

    private fun LearnWordActivity.markAnswerNeutral(
        llAnswer: LinearLayout,
        tvVariantValue: TextView,
        tvVariantNumb: TextView,
        clFinalScoreboard: ConstraintLayout,
        btSlip: Button
    ) {

        llAnswer.background = ContextCompat.getDrawable(
            this@LearnWordActivity,
            R.drawable.shape_rounded_containers_neutral
        )



        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@LearnWordActivity,
                R.color.textVariantsColor
            )
        )


        tvVariantNumb.apply {
            background = ContextCompat.getDrawable(
                this@LearnWordActivity,
                R.drawable.shape_rounded_contains
            )
            setTextColor(
                ContextCompat.getColor(
                    this@LearnWordActivity,
                    R.color.textVariantsColor
                )
            )
        }
        clFinalScoreboard.isVisible = false
        btSlip.isVisible = true

    }

    private fun showResultMessage(isCorrect: Boolean) {
        val color: Int
        val message: String
        val resultIcon: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.trueColor)
            message = getString(R.string.text_correct)
            resultIcon = R.drawable.ic_correct_answer
        } else {
            color = ContextCompat.getColor(this, R.color.falseColor)
            message = getString(R.string.text_wrong)
            resultIcon = R.drawable.ic_wrong_answer
        }
        with(binding){
            btSlip.isVisible = false
            clFinalScoreboard.isVisible= true
            btFinalContinue.setTextColor(color)
            clFinalScoreboard.setBackgroundColor(color)
            tvFinalScoreboard.text = message
            ivFinalImage.setImageResource(resultIcon)

        }

    }
}