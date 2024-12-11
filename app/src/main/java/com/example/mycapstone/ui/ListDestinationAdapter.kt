package com.example.mycapstone.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycapstone.databinding.ItemListPlaceholderBinding
import com.example.mycapstone.response.CategoryId

class ListDestinationAdapter: ListAdapter<CategoryId, ListDestinationAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemListPlaceholderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, data: CategoryId) {
            Glide.with(binding.itemIv)
                .load(data.image)
                .into(binding.itemIv)
            binding.nameEventTv.text = data.name
            binding.summaryEventTv.text = data.dateCreated

            binding.root.setOnClickListener {
                Intent(context, DetailDestinationActivity::class.java).also { intent ->
                    intent.putExtra(DetailDestinationActivity.EXTRA_ID,data.id)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListPlaceholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val events = getItem(position)
        holder.bind(holder.itemView.context,events)
    }

    companion object {
        val DIFF_CALLBACK = object: DiffUtil.ItemCallback<CategoryId>() {
            override fun areItemsTheSame(
                oldItem: CategoryId,
                newItem: CategoryId
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CategoryId,
                newItem: CategoryId
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

}
