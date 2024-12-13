package com.example.mycapstone.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycapstone.R
import com.example.mycapstone.database.Wishlist
import com.example.mycapstone.databinding.FragmentFavoriteBinding
import com.example.mycapstone.databinding.FragmentHomeBinding
import com.example.mycapstone.ui.ListFavoriteAdapter
import com.example.mycapstone.ui.ViewModelFactory


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoriteViewModel: FavoriteViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = obtainViewModel(requireActivity())

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recylerview.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.recylerview.addItemDecoration(itemDecoration)

        favoriteViewModel.getAllFavorites().observe(viewLifecycleOwner) { data ->
            setEventsData(data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setEventsData(consumerReviews: List<Wishlist>) {
        val adapter = ListFavoriteAdapter()
        adapter.submitList(consumerReviews)
        binding.recylerview.adapter = adapter
    }
    private fun obtainViewModel(activity: FragmentActivity): FavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }


}