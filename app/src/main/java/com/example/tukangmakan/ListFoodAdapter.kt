package com.example.tukangmakan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tukangmakan.databinding.ItemRowFoodBinding

//membuat adapter
class ListFoodAdapter(private val listFood: ArrayList<Food>) :
    RecyclerView.Adapter<ListFoodAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack){
     this.onItemClickCallBack = onItemClickCallBack
    }

    class ListViewHolder(var binding: ItemRowFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
       val binding = ItemRowFoodBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listFood.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listFood[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description
        holder.itemView.setOnClickListener {onItemClickCallBack.onItemClicked(listFood[holder.adapterPosition]) }
    }

    interface OnItemClickCallBack {
        fun onItemClicked(data: Food)
    }
}