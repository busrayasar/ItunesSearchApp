package com.busrayasar.itunessearchapp.ui.base

import android.arch.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.busrayasar.itunessearchapp.data.api.ApiHelperClass
import com.busrayasar.itunessearchapp.data.repo.iTunesRepo
import com.busrayasar.itunessearchapp.ui.main.viewmodel.ITunesViewModel

class ViewModelFactory(private val apiHelper: ApiHelperClass): ViewModelProvider.Factory {
    override fun <T: ViewModel ?> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(ITunesViewModel::class.java))
            return ITunesViewModel(iTunesRepo(apiHelper)) as T
        throw IllegalArgumentException("Undefined class name !")
    }
}