package com.example.englishwordsappn

data class Word(
    val word: String,
    val translation: String,
    var learned: Boolean = false,
)

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)

const val NUMBER_OF_ANSWERS: Int = 4

class LearningWords {
    companion object {
        val dictionary = listOf(
            Word("apple", "яблоко"),
            Word("banana", "банан"),
            Word("orange", "апельсин"),
            Word("grape", "виноград"),
            Word("kiwi", "киви"),
            Word("peach", "персик"),
            Word("pear", "груша"),
            Word("strawberry", "клубника"),
            Word("watermelon", "арбуз"),
            Word("pineapple", "ананас")
        )

        fun learnedCount(): Int = dictionary.count { it.learned }
        fun notLearnedCount(): Int = dictionary.count { !it.learned }
    }


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
