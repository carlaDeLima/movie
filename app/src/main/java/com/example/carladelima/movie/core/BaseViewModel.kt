package com.example.carladelima.movie.core

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val onError = SingleLiveEvent<StringWrapper>()

    fun setupErrorSnackbar(owner: LifecycleOwner,
                           context: Context?,
                           view: View,
                           onCall: () -> Unit = {}){

        onError.observe(owner, Observer {
            if(it != null){
                onCall()
                //showSnackbar(SnackbarType.ERROR, view, it.getMessage(context))
            }
        })
    }

}