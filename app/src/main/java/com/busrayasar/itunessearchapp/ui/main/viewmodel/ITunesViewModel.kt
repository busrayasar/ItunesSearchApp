package com.busrayasar.itunessearchapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busrayasar.itunessearchapp.data.model.Model
import com.busrayasar.itunessearchapp.data.repo.iTunesRepo
import com.busrayasar.itunessearchapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ITunesViewModel(private val iTunesRepository: iTunesRepo): ViewModel() {
    val data = MutableLiveData<Resource<Model>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchPosts(query: String, limit: Int){
        data.postValue(Resource.loading(null))
        Log.e("queryViewModel", query)
        iTunesRepo.searchMoviesFromQuery(query, limit)
        ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread()?.let {
                compositeDisposable.add(
                    it.subscribe({model ->
                        data.value = Resource.success(model)
                        Log.e("movielist", model.toString())

                    }, {
                        Log.e("error", data.value.toString())
                        data.value = Resource.error("An error occured", null)
                    })
                )
            })
    }

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun searchQuery(): MutableLiveData<Resource<Model>>{
        return data
    }


}