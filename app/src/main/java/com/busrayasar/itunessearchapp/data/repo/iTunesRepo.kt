package com.busrayasar.itunessearchapp.data.repo

import android.util.Log
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.model.Model
import io.reactivex.Single

class iTunesRepo(private val apiHelper: ApiHelperClass) {
    //ApiService'deki getQuery metodu ile query textini içeren limit kadar itemı çek
    fun searchMoviesFromQuery(query: String, limit: Int): Single<Model>{
        Log.e("queryRepository", query)
        return apiHelper.searchMoviesFromQuery(query, limit, "feature_movie")
    }
    fun searchMusicFromQuery(query: String, limit: Int): Single<Model>{
        Log.e("queryRepository", query)
        return apiHelper.searchMoviesFromQuery(query, limit, "track")
    }
    fun searchEbookFromQuery(query: String, limit: Int): Single<Model>{
        Log.e("queryRepository", query)
        return apiHelper.searchMoviesFromQuery(query, limit, "feature_ebook")
    }
    fun searchPodcastFromQuery(query: String, limit: Int): Single<Model>{
        Log.e("queryRepository", query)
        return apiHelper.searchMoviesFromQuery(query, limit, "feature_podcast")
    }
}