package com.frankz.a04_spotify.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.activities.SearchActivity
import com.frankz.a04_spotify.models.CategoryCardItem

class CategoryItemAdapter(
    private val context: SearchActivity,
    private val categoryItemsList: ArrayList<CategoryCardItem>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<CategoryItemAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val imageView: ImageView
        val relativeLayout: RelativeLayout

        init {
            titleTextView = view.findViewById(R.id.tv_title)
            imageView = view.findViewById(R.id.iv_item)
            relativeLayout = view.findViewById(R.id.rl_category)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.category_card,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = categoryItemsList[position]

        holder.titleTextView.text = item.title
        holder.imageView.setImageResource(item.imageResId)
        holder.relativeLayout.setBackgroundColor(Color.parseColor(item.backgroundColor))
    }

    override fun getItemCount(): Int {
        return this.categoryItemsList.size
    }
}