package com.busrayasar.itunessearchapp.ui.main.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.busrayasar.itunessearchapp.data.model.Model
import com.busrayasar.itunessearchapp.data.repo.iTunesRepo
import com.busrayasar.itunessearchapp.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()

    val text: LiveData<String> = Transformations.map(_index) {
        "Tab Page: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index


    }
}