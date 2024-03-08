package com.example.tukangmakan


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var rvFood: RecyclerView
    private val list = ArrayList<Food>()
    private lateinit var button : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.prime)))

        //membuat fungsi float button share ke copy link profile instagram
        button = findViewById(R.id.float_btn)
        val urlIg = "https://www.instagram.com/haibasss?igsh=eTJ4Z2JpNm10NTJy"
        button.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra("Share This",urlIg)
            val  chooser = Intent.createChooser(intent,"Share Using...")
            startActivity(chooser)
        }

        //memanggil list dari data class dan menambahkan ke main activity
        rvFood = findViewById(R.id.rv_food)
        rvFood.setHasFixedSize(true)
        list.addAll(getListFood())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.person_info){
        val intent = Intent(this,PersonalInfo::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFood(): java.util.ArrayList<Food> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFood = ArrayList<Food>()
        for (i in dataName.indices) {
            val food = Food(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listFood.add(food)
        }
        return listFood
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        rvFood.adapter = listFoodAdapter
        listFoodAdapter.setOnItemClickCallBack(object : ListFoodAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Food) {
                showSelectedFood(data)
            }
        })
    }
    private fun showSelectedFood(food:Food){
        Toast.makeText(this,"kamu memilih "+ food.name, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, FoodDetailActivity::class.java)
        intent.putExtra(FoodDetailActivity.EXTRA_FOOD, food)
        startActivity(intent)
    }
}