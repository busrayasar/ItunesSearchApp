package com.busrayasar.itunessearchapp.data.api

import com.busrayasar.itunessearchapp.data.model.Model
import com.busrayasar.itunessearchapp.utils.Constants
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImplementation : ApiService {
    //request
    override fun getQuery(term: String, limit: Int, wrapper : String): Single<Model> {
        return Rx2AndroidNetworking.get(Constants.BASE_URL)
            .addQueryParameter("term", term)
            .addQueryParameter("limit", limit.toString())
            .addQueryParameter("wrapperType",)
            .build()
            .getObjectSingle(Model::class.java)
    }

}
//2