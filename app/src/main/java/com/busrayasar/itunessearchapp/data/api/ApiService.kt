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
        @Query("limit") limit: Int,
        @Query("wrapperType") wrapperType: String
    ):Single<Model>
}

//RxJava'da Single, Observable vs. kullanılır, kullanım açısından daha olanakları var
/*
* Single<T> -> Emits either a single item or an error event. The reactive version of a method call.
* Yani bir tane single item alıyor, veriyi bir kere çekiyor ve duruyor
* */

//1