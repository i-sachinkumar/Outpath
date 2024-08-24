package com.example.outpath.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.outpath.adapter.FoodListAdapter
import com.example.outpath.R
import com.example.outpath.SharedViewModel
import com.example.outpath.databinding.FragmentSelectFoodItemBinding
import com.example.outpath.sampleData.SampleData

class MealSelectionFragment : Fragment() {

    companion object {

        private const val ARG_STRING = "journey_details"

        fun newInstance(value: String): MealSelectionFragment {
            val fragment = MealSelectionFragment()
            val args = Bundle()
            args.putString(ARG_STRING, value)
            fragment.arguments = args
            return fragment
        }
    }


    private lateinit var binding: FragmentSelectFoodItemBinding
    private lateinit var adapter: FoodListAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_select_food_item, container, false)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        // Retrieve the string value from arguments
        val value = arguments?.getString(ARG_STRING)

        val foodList =
            if (value.equals("Journey1", true))
                SampleData.getJourney1Data()
            else SampleData.getJourney2Data()

        adapter = FoodListAdapter(foodList, journeyDetails = value!!, sharedViewModel = sharedViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        sharedViewModel.selectedFoodItem.observe(viewLifecycleOwner){
            adapter.notifyDataSetChanged()
        }


        return binding.root
    }
}