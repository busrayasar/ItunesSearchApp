package com.busrayasar.itunessearchapp.data.api

import android.util.Log
import com.busrayasar.itunessearchapp.data.model.Model
import io.reactivex.Single

class ApiHelperClass(private val apiService: ApiService) {
    fun searchMoviesFromQuery(query: String, limit: Int): Single<Model>{
        Log.e("queryApiHelper", query)
        return apiService.getQuery(query, limit)
    }
}