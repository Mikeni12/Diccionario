package com.mikeni.diccionario.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mikeni.diccionario.model.DefinitionResponse
import com.mikeni.diccionario.repository.DictionaryRepository
import kotlinx.coroutines.launch

class DictionaryViewModel(application: Application) : BaseViewModel(application) {

    private val dictionaryRepository = DictionaryRepository()

    val definition = MutableLiveData<DefinitionResponse>()

    fun getDefinition(word: String){
        launch {
            try {
                val response = dictionaryRepository.getDefinition(word)
                Log.d("HTTP", response.toString())
                definition.value = response[0]
            } catch (e: Throwable) {
                Log.d("HTTP", e.localizedMessage)
            }
        }
    }
}