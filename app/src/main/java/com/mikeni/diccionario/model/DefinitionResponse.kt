package com.mikeni.diccionario.model

data class DefinitionResponse(
    val meanings: List<Meaning>,
    val origin: String,
    val phonetic: String,
    val word: String
) {
    data class Meaning(
        val definitions: List<Definition>,
        val partOfSpeech: String
    ) {
        data class Definition(
            val definition: String
        )
    }
}