package com.example.mycapstone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.R
import com.example.mycapstone.databinding.FragmentHomeBinding
import com.example.mycapstone.response.DataItem
import com.example.mycapstone.ui.ListDestinationAdapter


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDestinations.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvDestinations.addItemDecoration(itemDecoration)

        mainViewModel.listDestination.observe(requireActivity()) { listDestination ->
            if (listDestination != null) {
                setEventsData(listDestination)
            }
        }

        mainViewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setEventsData(listDestination: List<DataItem>) {
        val adapter = ListDestinationAdapter()
        adapter.submitList(listDestination)
        binding.rvDestinations.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pro.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}