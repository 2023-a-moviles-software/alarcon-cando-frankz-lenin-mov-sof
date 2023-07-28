package com.frankz.a04_spotify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frankz.a04_spotify.MainActivity
import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.CardItem

class ForYouItemAdapter(
    private val context: MainActivity,
    private val cardItemsList: ArrayList<CardItem>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<ForYouItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val imageView: ImageView

        init {
            titleTextView = view.findViewById(R.id.tv_title)
            imageView = view.findViewById(R.id.iv_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_layout,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = cardItemsList[position]
        print("Estableciendo titulo")
        print(item.title)
        holder.titleTextView.text = item.title
        holder.imageView.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int {
        return this.cardItemsList.size
    }
}