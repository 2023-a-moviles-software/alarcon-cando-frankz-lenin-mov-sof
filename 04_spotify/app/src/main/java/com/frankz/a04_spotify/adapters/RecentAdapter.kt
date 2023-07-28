package com.frankz.a04_spotify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.frankz.a04_spotify.R
import com.frankz.a04_spotify.models.AlbumItem

class RecentAdapter(
    private val context: AppCompatActivity,
    private val recentItemsList: ArrayList<AlbumItem>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecentAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTextView: TextView
        val subtitleTextView: TextView
        val imageView: ImageView

        init {
            titleTextView = view.findViewById(R.id.tv_title)
            imageView = view.findViewById(R.id.iv_item)
            subtitleTextView = view.findViewById(R.id.tv_subtitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.recient_card_layout,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = recentItemsList[position]

        holder.titleTextView.text = item.title
        holder.imageView.setImageResource(item.imageResId)
        holder.subtitleTextView.text = item.subtitle
    }

    override fun getItemCount(): Int {
        return this.recentItemsList.size
    }
}