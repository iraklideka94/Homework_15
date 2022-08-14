package com.example.homework_15

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework_15.Resource.Resource
import com.example.homework_15.ViewModels.RegistrationViewModel
import com.example.homework_15.databinding.FragmentRegBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class RegFragment : Fragment() {
    private var _binding: FragmentRegBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.registerState.collect{
                    when(it){
                        is Resource.Success ->{Snackbar.make(view,"success registration",Snackbar.LENGTH_SHORT).show()}
                        is Resource.Error -> {Snackbar.make(view,it.errorData,Snackbar.LENGTH_SHORT).show()}
                        else -> {}
                    }
                }
            }
        }
    }

}