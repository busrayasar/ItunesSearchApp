package com.busrayasar.itunessearchapp.data.api

import com.busrayasar.itunessearchapp.data.model.Model
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("")
    fun getQuery(
        @Query("term") term: String,
        @Query("limit") limit: Int
    ):Single<Model>
}