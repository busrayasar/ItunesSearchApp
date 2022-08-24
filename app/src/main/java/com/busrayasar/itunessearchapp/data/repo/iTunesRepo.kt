package com.busrayasar.itunessearchapp.data.repo

import android.util.Log
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.model.Model
import io.reactivex.Single

class iTunesRepo(private val apiHelper: ApiHelperClass) {

    fun searchMoviesFromQuery(query: String, limit: Int): Single<Model>{
        Log.e("queryRepository", query)
        return apiHelper.searchMoviesFromQuery(query, limit)
    }

}