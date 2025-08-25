package com.example.englishwordsappn.domain.trainer

import com.example.englishwordsappn.domain.model.Question
import com.example.englishwordsappn.domain.model.Word
import com.example.englishwordsappn.data.WordsRepository.words as dictionary


const val NUMBER_OF_ANSWERS: Int = 4

class LearningWords {

    private var _currentQuestion: Question? = null
    val currentQuestion: Question? get() = _currentQuestion

    fun getNextQuestion(): Question? {
        val notLearnedList = dictionary.filter { !it.learned }
        if (notLearnedList.isEmpty()) {
            _currentQuestion = null
            return null
        }

        val variants: List<Word> = if (notLearnedList.size < NUMBER_OF_ANSWERS) {
            val learnedPool = dictionary.filter { it.learned }.shuffled()
            (notLearnedList + learnedPool.take(NUMBER_OF_ANSWERS - notLearnedList.size)).shuffled()
        } else {
            notLearnedList.shuffled().take(NUMBER_OF_ANSWERS)
        }

        val correct = variants.random()
        _currentQuestion = Question(variants = variants, correctAnswer = correct)
        return _currentQuestion
    }

    fun checkAnswer(userAnswerIndex: Int): Boolean {
        val q = _currentQuestion ?: return false
        val correctIndex = q.variants.indexOf(q.correctAnswer)
        val ok = userAnswerIndex == correctIndex
        if (ok) q.correctAnswer.learned = true
        return ok
    }

    fun correctTranslation(): String =
        _currentQuestion?.correctAnswer?.translation.orEmpty()

    fun correctIndex(): Int =
        _currentQuestion?.let { it.variants.indexOf(it.correctAnswer) } ?: -1
}
