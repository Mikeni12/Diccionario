package com.mikeni.diccionario.repository

import com.mikeni.diccionario.utils.Constants

class DictionaryRepository {
    private val googleService = ServiceBuilder(Constants.google).buildService(DictionaryAPI::class.java)

    suspend fun getDefinition(word: String) = googleService.getDefinition("es", word)
}