package com.example.carladelima.movie.app.movie.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.favorite.FavoriteViewModel
import com.example.carladelima.movie.app.movie.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_favoritos.*


class FavoriteFragment : Fragment() {

    private lateinit var movieViewModel: FavoriteViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieViewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieAdapter = MovieAdapter()
        favoritosRecyclerView.adapter = movieAdapter

        movieViewModel.listMovieFavorite()
        subscribeUI()

    }

    private fun subscribeUI() {
        with(movieViewModel){

            movieFavorite.observe(this@FavoriteFragment, Observer { movies ->
                movies?.let {
                    movieAdapter.refresh(it.results)
                }
            })

            onError.observe(this@FavoriteFragment, Observer { error ->
                error?.let {
                }
            })

            onRequestMovieFavoriteStarted.observe(this@FavoriteFragment, Observer {
                progress.visibility = View.VISIBLE
            })

            onRequestMovieFavoriteFinished.observe(this@FavoriteFragment, Observer {
                progress.visibility = View.GONE
            })
        }
    }
}