package com.example.movie.data.remote.model.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    @SerialName("page")
    val page: Int?,
    @SerialName("results")
    val results: List<Result?>?,
    @SerialName("totalPages")
    val total_pages: Int?,
    @SerialName("total_results")
    val totalResults: Int?
)