package com.busrayasar.itunessearchapp.data.api

import android.util.Log
import com.busrayasar.itunessearchapp.data.model.Model
import io.reactivex.Single

class ApiHelperClass(private val apiService: ApiService) {
    //ApiService'deki getQuery metodu ile query textini içeren limit kadar itemı çek
    fun searchMoviesFromQuery(query: String, limit: Int, wrapper : String): Single<Model>{
        Log.e("queryApiHelper", query)
        return apiService.getQuery(query, limit, wrapper)
    }
    fun searchMusicFromQuery(query: String, limit: Int, wrapper : String): Single<Model>{
        Log.e("queryApiHelper", query)
        return apiService.getQuery(query, limit, wrapper)
    }
    fun searchEbookFromQuery(query: String, limit: Int, wrapper : String): Single<Model>{
        Log.e("queryApiHelper", query)
        return apiService.getQuery(query, limit,wrapper)
    }
    fun searchPodcastFromQuery(query: String, limit: Int, wrapper : String): Single<Model>{
        Log.e("queryApiHelper", query)
        return apiService.getQuery(query, limit, wrapper)
    }
}
//3