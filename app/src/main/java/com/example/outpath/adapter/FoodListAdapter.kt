package com.example.outpath.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.outpath.R
import com.example.outpath.SharedViewModel
import com.example.outpath.model.FoodItem


class FoodListAdapter(
    private val characters: List<FoodItem>,
    private val journeyDetails: String,
    private val sharedViewModel: SharedViewModel
) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodItemName: TextView = itemView.findViewById(R.id.food_name)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val addBtn : Button =  itemView.findViewById(R.id.addBtn)

        fun bind(foodItem: FoodItem) {
            foodItemName.text = foodItem.name
            price.text = "₹ ${foodItem.price}"

            if(sharedViewModel.selectedFoodItem.value?.contains(foodItem) == true){
                addBtn.text = "Added"
                addBtn.backgroundTintList = ContextCompat.getColorStateList(addBtn.context,
                    R.color.selected_food_item_bg
                )
                addBtn.setOnClickListener {
                    sharedViewModel.removeItem(foodItem)
                    sharedViewModel.map.remove(journeyDetails)
                }
            }
            else{
                addBtn.text = "Add"
                addBtn.backgroundTintList = ContextCompat.getColorStateList(addBtn.context,
                    R.color.white
                )
                addBtn.setOnClickListener {
                    if(!sharedViewModel.map.contains(journeyDetails)){
                        sharedViewModel.map[journeyDetails] = foodItem
                        sharedViewModel.addItem(foodItem)
                    }
                    else{
                        Toast.makeText(itemView.context, "Only one item can be selected", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_lists_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size
}