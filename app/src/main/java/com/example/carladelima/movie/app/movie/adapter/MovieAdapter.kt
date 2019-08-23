package com.example.carladelima.movie.app.movie.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.app.movie.view.activity.DetalheMovieActivity
import com.example.carladelima.movie.core.BaseAdapter
import com.example.carladelima.movie.core.BaseViewHolder
import com.example.carladelima.movie.core.Paths
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_movie.view.*

class MovieAdapter : BaseAdapter<Movie, BaseViewHolder<Movie>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_movie, parent, false))
    }

    class MovieViewHolder(view: View) : BaseViewHolder<Movie>(view) {

        override fun bind(item: Movie) {
            with(itemView) {
                nameMovie.text = item.title

                Picasso
                    .with(context)
                    .load(Paths.urlImage + item.poster_path)
                    .centerCrop()
                    .resize(125, 188)
                    .into(imageMovie)

                imageMovie.setOnClickListener {
                    val intent = Intent(context, DetalheMovieActivity::class.java)
                    intent.putExtra("ID_MOVIE", item.id)
                    context.startActivity(intent)
                }
            }
        }
    }
}