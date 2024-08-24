package com.example.outpath.screen

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.outpath.R
import com.example.outpath.SharedViewModel
import com.example.outpath.databinding.FragmentHomeBinding
import com.example.outpath.sampleData.SampleData
import com.google.android.material.button.MaterialButton

class HomeFragment: Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        binding.selectMealBtn.setOnClickListener {
            sharedViewModel.currentJourney = "Journey1"
            sharedViewModel.setOnHomeScreen(false)
        }

        binding.backBtn.setOnClickListener {
            sharedViewModel.setOnHomeScreen(true)
        }

        sharedViewModel.onHomeScreen.observe(viewLifecycleOwner){

            changeSelection(binding.upJourneyBtn, true)
            changeSelection(binding.downJourneyBtn, false)

            binding.backBtn.visibility = if(it) View.GONE else View.VISIBLE
            binding.skipBtn.visibility = if(it) View.GONE else View.VISIBLE
            binding.proceedBtn.visibility = if(it) View.GONE else View.VISIBLE
            binding.sectionButtons.visibility = if(it) View.GONE else View.VISIBLE

            binding.selectMealBtn.visibility = if(it) View.VISIBLE else View.GONE
            binding.listFragmentContainer.visibility = if(it) View.GONE else View.VISIBLE

            var mealSelectionFragment = MealSelectionFragment.newInstance("Journey1")
            parentFragmentManager.beginTransaction()
                .replace(R.id.list_fragment_container, mealSelectionFragment)
                .commit()


            binding.upJourneyBtn.setOnClickListener {
                sharedViewModel.currentJourney = "Journey1"
                mealSelectionFragment = MealSelectionFragment.newInstance("Journey1")

                changeSelection(binding.upJourneyBtn, true)
                changeSelection(binding.downJourneyBtn, false)

                parentFragmentManager.beginTransaction()
                    .replace(R.id.list_fragment_container, mealSelectionFragment)
                    .commit()
            }

            binding.downJourneyBtn.setOnClickListener {
                sharedViewModel.currentJourney = "Journey2"
                mealSelectionFragment = MealSelectionFragment.newInstance("Journey2")

                changeSelection(binding.upJourneyBtn, false)
                changeSelection(binding.downJourneyBtn, true)

                parentFragmentManager.beginTransaction()
                    .replace(R.id.list_fragment_container, mealSelectionFragment)
                    .commit()

            }

            binding.proceedBtn.setOnClickListener {
                if(sharedViewModel.currentJourney.equals("Journey1", true)){
                    sharedViewModel.currentJourney = "Journey2"
                    mealSelectionFragment = MealSelectionFragment.newInstance("Journey2")

                    changeSelection(binding.upJourneyBtn, false)
                    changeSelection(binding.downJourneyBtn, true)

                    parentFragmentManager.beginTransaction()
                        .replace(R.id.list_fragment_container, mealSelectionFragment)
                        .commit()
                }
                else{
                    //finish
                    Toast.makeText(requireContext(), "Completed all sections", Toast.LENGTH_SHORT).show()
                }
            }

        }


        return binding.root
    }

    private fun changeSelection(button: MaterialButton, selected: Boolean){
        val strokeColor : ColorStateList?
        val backgroundTint: ColorStateList?
        if(selected){
            strokeColor = ContextCompat.getColorStateList(requireContext(), R.color.selected_stroke_orange)
            backgroundTint = ContextCompat.getColorStateList(requireContext(), R.color.selected_light_orange)
        }
        else{
            strokeColor = ContextCompat.getColorStateList(requireContext(), R.color.light_grey)
            backgroundTint = ContextCompat.getColorStateList(requireContext(), R.color.white)
        }
        button.apply {
            this.strokeColor = strokeColor
            this.backgroundTintList = backgroundTint
        }
    }
}