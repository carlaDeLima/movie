package com.example.carladelima.movie.app.movie.view.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.favorite.FavoriteViewModel
import com.example.carladelima.movie.app.movie.database.MovieDatabase
import com.example.carladelima.movie.app.movie.model.Movie
import com.example.carladelima.movie.core.BaseActivity
import com.example.carladelima.movie.core.Paths
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_movie.*

class DetalheMovieActivity : BaseActivity() {

    companion object {
        const val ID_MOVIE = "ID_MOVIE"
    }

    private lateinit var movieViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_movie)

        movieViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)

        val id = intent.getIntExtra(ID_MOVIE, -1)
        val movie = MovieDatabase.searchMovie(id)

        movie?.let { m ->
            favoritar.setOnClickListener {
                if (m.favorite) {
                    favoritar.setBackgroundResource(R.drawable.ic_favorite_border)
                } else {
                    favoritar.setBackgroundResource(R.drawable.ic_favorite)
                }
                movieViewModel.favoritar(m.id)
            }
        }

        bind(movie)
        subscribeUI()
    }

    private fun subscribeUI() {
        with(movieViewModel) {

            onRequestMovieFavoriteStarted.observe(this@DetalheMovieActivity, Observer {
                progress.visibility = View.VISIBLE
            })

            onRequestMovieFavoriteFinished.observe(this@DetalheMovieActivity, Observer {
                progress.visibility = View.GONE
            })

        }
    }

    private fun bind(movie: Movie?) {
        movie?.let { m ->
            Picasso
                .with(this)
                .load(Paths.urlImage + m.poster_path)
                .centerCrop()
                .fit()
                .into(imageMovie)
            titleMovie.text = m.title
            overviewMovie.text = m.overview
            dataMovie.text = m.release_date
            if (m.favorite) {
                favoritar.setBackgroundResource(R.drawable.ic_favorite)
            } else {
                favoritar.setBackgroundResource(R.drawable.ic_favorite_border)
            }
        }
    }
}