package com.example.carladelima.movie.app.movie.model

open class MoviesGeral(
        var page: Int,
        var total_results: Int,
        var total_pages: Int,
        var results: List<Movie> = listOf()
)