package com.example.carladelima.movie.app.movie.view.fragment

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.movie.adapter.MovieAdapter
import com.example.carladelima.movie.app.movie.view_model.MovieViewModel
import com.example.carladelima.movie.core.BaseFragment
import kotlinx.android.synthetic.main.fragment_filme.*

class FilmesFragment : BaseFragment() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        return inflater.inflate(R.layout.fragment_filme, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieAdapter = MovieAdapter()


        movieViewModel.listMoviePopular()
        subscribeUI()


        filmesRecyclerView.adapter = movieAdapter

    }

    private fun subscribeUI() {
        with(movieViewModel){

            movies.observe(this@FilmesFragment, Observer { movies ->
                movies?.let {
                    movieAdapter.refresh(it.results)
                }
            })

            onError.observe(this@FilmesFragment, Observer { error ->
                error?.let {
                }
            })

            onRequestMovieStarted.observe(this@FilmesFragment, Observer {
                progress.visibility = View.VISIBLE
            })

            onRequestMovieFinished.observe(this@FilmesFragment, Observer {
                progress.visibility = View.GONE
            })
        }
    }
}