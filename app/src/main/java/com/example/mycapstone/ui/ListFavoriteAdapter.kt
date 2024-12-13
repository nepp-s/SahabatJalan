package com.example.mycapstone.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycapstone.database.Wishlist
import com.example.mycapstone.databinding.ItemListPlaceholderBinding

class ListFavoriteAdapter: ListAdapter<Wishlist, ListFavoriteAdapter.MyViewHolder>(DIFF_CALLBACK) {

    inner class MyViewHolder(val binding: ItemListPlaceholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, data: Wishlist) {
            Glide.with(binding.itemIv)
                .load(data.imageLocation)
                .into(binding.itemIv)
            binding.namePlaceIv.text = data.name
            binding.locationTv.text = data.location

            binding.root.setOnClickListener {
                Intent(context, DetailDestinationActivity::class.java).also { intent ->
                    intent.putExtra(DetailDestinationActivity.EXTRA_ID,data.id)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFavoriteAdapter.MyViewHolder {
        val binding = ItemListPlaceholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListFavoriteAdapter.MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(holder.itemView.context, data)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Wishlist>() {
            override fun areItemsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Wishlist, newItem: Wishlist): Boolean {
                return oldItem == newItem
            }

        }
    }

}