package com.hashinology.retrofitmvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hashinology.retrofitmvvm.R
import com.hashinology.retrofitmvvm.models.Movies

class MoviesAdapter(val movies: Movies, val context: Context): RecyclerView.Adapter<MoviesAdapter.ViewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)
        return ViewsHolder(view)
    }

    override fun onBindViewHolder(holder: ViewsHolder, position: Int) {
        val data = movies.get(position)
        Glide.with(holder.itemView.context)
            .load(data.imageUrl)
            .into(holder.movieImg)
        holder.movieName.text = data.name
        holder.movieDesc.text = data.desc
        holder.movieCategory.text = data.category
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val movieName = itemView.findViewById<TextView>(R.id.tvMovieName)
        val movieImg = itemView.findViewById<ImageView>(R.id.ivMovie)
        val movieDesc = itemView.findViewById<TextView>(R.id.tvMovieDesc)
        val movieCategory = itemView.findViewById<TextView>(R.id.tvMovieCategory)
    }
}