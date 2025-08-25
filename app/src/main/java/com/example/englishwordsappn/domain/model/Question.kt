package com.example.englishwordsappn.domain.model

data class Question(
    val variants: List<Word>,
    val correctAnswer: Word,
)
