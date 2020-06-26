package com.mikeni.diccionario.repository

import com.mikeni.diccionario.model.DefinitionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPI {
    @GET("entries/{lang}/{word}")
    suspend fun getDefinition(
        @Path("lang") lang: String,
        @Path("word") word: String
    ): List<DefinitionResponse>
}