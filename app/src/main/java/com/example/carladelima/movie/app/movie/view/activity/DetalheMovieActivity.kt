package com.example.carladelima.movie.app.movie.view.activity

import android.os.Bundle
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.movie.database.MovieDatabase
import com.example.carladelima.movie.core.BaseActivity
import com.example.carladelima.movie.core.Paths
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_movie.*

class DetalheMovieActivity : BaseActivity() {

    companion object {
        const val ID_MOVIE = "ID_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_movie)
        val id = intent.getIntExtra(ID_MOVIE, -1)
        val movie = MovieDatabase.searchMovie(id)

        movie?.let {
            Picasso
                .with(this)
                .load(Paths.urlImage + it.poster_path)
                .centerCrop()
                .into(imageMovie)
            titleMovie.text = it.title
            overviewMovie.text = it.overview
            dataMovie.text = it.release_date
        }

        favoritar.setOnClickListener {
            it.setBackgroundResource(R.drawable.ic_favorite)
        }
    }
}