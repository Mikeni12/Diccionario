package com.mikeni.diccionario.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mikeni.diccionario.R
import com.mikeni.diccionario.viewmodel.DictionaryViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SearchFragment : Fragment() {

    private lateinit var viewModel: DictionaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view.findViewById<Button>(R.id.button_second).setOnClickListener {
        //    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        //}

        viewModel = ViewModelProvider(this)[DictionaryViewModel::class.java]
        viewModel.getDefinition("Hola")

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.definition.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("palabra", it.toString())
                val action = SearchFragmentDirections.actionDefinitionFragment(it)
                findNavController().navigate(action)
            }
        })
    }
}