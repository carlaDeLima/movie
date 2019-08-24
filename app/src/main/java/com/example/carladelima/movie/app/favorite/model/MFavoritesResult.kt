package com.example.carladelima.movie.app.favorite.model

import com.example.carladelima.movie.app.movie.model.Movie

open class MFavoritesResult(
    var page: Int = 0,
    var results: List<Movie> = listOf(),
    var total_pages: Int = 0,
    var total_results: Int = 0
)