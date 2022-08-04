package com.example.homework_14

import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_14.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val itemsAdapter by lazy { ItemsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler()
        observers()
    }
    private fun recycler() {
        binding.rv.apply {
            adapter = itemsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.getInfo()
    }

    private fun observers() {
        viewModel.getInfo()
        viewLifecycleOwner.lifecycleScope.launch {
                viewModel.data.collect {
                    when(it){
                        is LoggedState.OnSuccess -> itemsAdapter.submitList(it.data)
                        is LoggedState.OnError -> Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                        else -> {}
                }
            }
        }
    }


}