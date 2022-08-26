package com.busrayasar.itunessearchapp.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busrayasar.itunessearchapp.data.model.Model
import com.busrayasar.itunessearchapp.data.repo.iTunesRepo
import com.busrayasar.itunessearchapp.utils.Resource
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.newThread


class ITunesViewModel(private val iTunesRepository: iTunesRepo): ViewModel() {
    val data = MutableLiveData<Resource<Model>>()
    private val compositeDisposable = CompositeDisposable()

    //internetten veri çekme işlemleri
    fun fetchPosts(q: String, limit: Int) {
        data.postValue(Resource.loading(null))
        Log.e("queryViewModel",q)
        iTunesRepository.searchMoviesFromQuery(q,limit)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.let {
                compositeDisposable.add(
                    it.subscribe({ model ->
                        data.value = Resource.success(model)
                        Log.e("movieList", model.toString())
                    }, {
                        Log.e("ERR",data.value.toString())
                        data.value= Resource.error("Something went wrong", null)
                    })
                )
            }
    }

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.dispose()
    }
    fun searchQuery(): MutableLiveData<Resource<Model>>{
        return data
    }


}