package com.example.tukangmakan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tukangmakan.databinding.ActivityFoodDetailBinding

@Suppress("DEPRECATION")
class FoodDetailActivity : AppCompatActivity() {
    //mendapatkan key
    companion object {
        const val EXTRA_FOOD = "extra_food"
    }

    private lateinit var binding: ActivityFoodDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //move to get key and get info from food data
        val food = intent.getParcelableExtra<Food>(EXTRA_FOOD)
        food?.let { displayFoodDetails(it) }
    }

    private fun displayFoodDetails(food: Food) {
        binding.tvFoodName.text = food.name
        binding.tvFoodDescription.text = food.description
        binding.imgFood.setImageResource(food.photo)
    }
}