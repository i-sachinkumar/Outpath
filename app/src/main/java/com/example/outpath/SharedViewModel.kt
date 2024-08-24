package com.example.outpath

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.outpath.model.FoodItem

class SharedViewModel: ViewModel() {
    private val _selectedFoodItem = MutableLiveData<MutableList<FoodItem>>()
    val selectedFoodItem: LiveData<MutableList<FoodItem>>
        get() = _selectedFoodItem

    fun setCharacter(foodItems: MutableList<FoodItem>){
        _selectedFoodItem.value =  foodItems
    }

    init {
        _selectedFoodItem.value = mutableListOf()
    }

    fun addItem(item: FoodItem) {
        _selectedFoodItem.value?.add(item)
        _selectedFoodItem.value = _selectedFoodItem.value // Trigger LiveData update
    }

    fun removeItem(item: FoodItem) {
        _selectedFoodItem.value?.remove(item)
        _selectedFoodItem.value = _selectedFoodItem.value
        println(_selectedFoodItem.value)
    }



    private val _onHomeScreen = MutableLiveData(true)
    val onHomeScreen: LiveData<Boolean>
        get() = _onHomeScreen

    fun setOnHomeScreen(isItOnHome: Boolean){
        _onHomeScreen.value =  isItOnHome
    }


    //for each end segment one or zero food item will be stored
    val map: MutableMap<String, FoodItem> = mutableMapOf()

    var currentJourney: String = "Journey1"
}