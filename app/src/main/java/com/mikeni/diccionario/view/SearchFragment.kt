package com.mikeni.diccionario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mikeni.diccionario.R
import com.mikeni.diccionario.viewmodel.DictionaryViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]
        observeViewModel()

        edtWord.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.getDefinition(edtWord.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.definition.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = SearchFragmentDirections.actionDefinitionFragment(it)
                findNavController().navigate(action)
            }
        })
    }
}