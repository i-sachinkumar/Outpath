package com.example.outpath.sampleData

import com.example.outpath.model.FoodItem

class SampleData {
    companion object{
        fun getTotalSegment(): List<String>{
            return listOf("Journey1", "Journey2")
        }


        fun getJourney1Data() : List<FoodItem>{
            return listOf(
                FoodItem(id = 1, name = "Pizza", price = 799, "Journey1"),
                FoodItem(id = 2, name = "Burger", price = 399, "Journey1"),
                FoodItem(id = 3, name = "Pasta", price = 499, "Journey1"),
                FoodItem(id = 4, name = "Sushi", price = 1299, "Journey1"),
                FoodItem(id = 5, name = "Salad", price = 299, "Journey1"),
                FoodItem(id = 6, name = "Taco", price = 199, "Journey1"),
                FoodItem(id = 7, name = "Steak", price = 1499, "Journey1"),
                FoodItem(id = 8, name = "Ice Cream", price = 199, "Journey1"),
                FoodItem(id = 9, name = "Sandwich", price = 249, "Journey1"),
                FoodItem(id = 10, name = "Smoothie", price = 349, "Journey1")
            )
        }

        fun getJourney2Data(): List<FoodItem>{
            return listOf(
                FoodItem(id = 1, name = "Fries", price = 149, "Journey2"),
                FoodItem(id = 2, name = "Hotdog", price = 249, "Journey2"),
                FoodItem(id = 3, name = "Ramen", price = 899, "Journey2"),
                FoodItem(id = 4, name = "Dumplings", price = 699, "Journey2"),
                FoodItem(id = 5, name = "Cupcake", price = 99, "Journey2"),
                FoodItem(id = 6, name = "Nachos", price = 299, "Journey2"),
                FoodItem(id = 7, name = "Donut", price = 149, "Journey2"),
                FoodItem(id = 8, name = "Salad", price = 299, "Journey2"),
                FoodItem(id = 9, name = "Taco", price = 199, "Journey2"),
                FoodItem(id = 10, name = "Steak", price = 1499, "Journey2"),
            )
        }

    }
}