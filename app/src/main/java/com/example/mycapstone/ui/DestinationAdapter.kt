//package com.example.mycapstone.ui
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.mycapstone.R
//import com.example.mycapstone.data.Destination
//
//class DestinationAdapter : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {
//    private val items = mutableListOf<Destination>()
//
//    fun submitList(newItems: List<Destination>) {
//        items.clear()
//        items.addAll(newItems)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout., parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(items[position])
//    }
//
//    override fun getItemCount() = items.size
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        fun bind(destination: Destination) {
//            itemView.findViewById<TextView>(R.id.tvName).text = destination.name
//            itemView.findViewById<TextView>(R.id.tvLocation).text = destination.location
//        }
//    }
//}