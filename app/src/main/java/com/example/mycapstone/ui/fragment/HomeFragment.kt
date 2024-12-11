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
import com.example.mycapstone.response.CategoryId
import com.example.mycapstone.ui.ListDestinationAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDestinations.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvDestinations.addItemDecoration(itemDecoration)

        mainViewModel.categoryId.observe(requireActivity()) { consumerReviews ->
            setEventsData(consumerReviews)
        }

        mainViewModel.isLoading.observe(requireActivity()) {
            showLoading(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun setEventsData(consumerReviews: List<CategoryId>) {
        val adapter = ListDestinationAdapter()
        adapter.submitList(consumerReviews)
        binding.rvDestinations.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pro.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}