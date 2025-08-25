package com.example.englishwordsappn.domain.model

data class Word(
    val word: String,
    val translation: String,
    var learned: Boolean = false,
    )
