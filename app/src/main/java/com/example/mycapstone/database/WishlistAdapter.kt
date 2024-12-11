package com.example.mycapstone.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.R

class WishlistAdapter (private val favoriteList: List<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>(){

    class WishlistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.favoriteName)
        val mediaImageView: ImageView = view.findViewById(R.id.favoriteMediaCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return WishlistViewHolder(view)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val favoriteEvent = favoriteList[position]
        holder.nameTextView.text = favoriteEvent.name
        Glide.with(holder.mediaImageView).load(favoriteEvent.categoryId).into(holder.mediaImageView)
    }

    override fun getItemCount() = favoriteList.size
}
