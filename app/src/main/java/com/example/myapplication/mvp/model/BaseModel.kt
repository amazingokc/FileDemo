package com.example.myapplication.mvp.model

import androidx.lifecycle.*

abstract class BaseModel : IModel, LifecycleEventObserver {

    override fun onDetach(source: LifecycleOwner) {
        source.lifecycle.removeObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            onDetach(source)
        }
    }

    abstract suspend fun getLoalData(): Any?
    abstract suspend fun getRemoteData(): Any?

}