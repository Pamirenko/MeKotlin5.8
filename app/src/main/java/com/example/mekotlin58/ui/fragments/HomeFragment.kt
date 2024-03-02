package com.example.mekotlin58.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mekotlin58.databinding.FragmentHomeBinding
import com.example.mekotlin58.ui.adapters.NewsAdapter
import com.example.mekotlin58.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val newsAdapter = NewsAdapter()
    private val viewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        search()
        subscribeNews()
    }

    private fun initialize() {
        binding.rvSearch.adapter = newsAdapter

    }

    private fun search() = with(binding) {
        edText.addTextChangedListener {
            viewModel.searchNew(it.toString())
        }
    }

    private fun subscribeNews() {
        viewModel.newsLiveData.observe(viewLifecycleOwner) { uiState ->
            uiState?.let {
                Log.e("tag", "uiState${uiState.success}")
                if (it.success != null) {
                    Log.e("taga", "ghjkl:${it}")
                    newsAdapter.setNewsList(it.success)

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}