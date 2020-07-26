package com.example.myapplication.mvp.model

import androidx.lifecycle.LifecycleOwner

interface IModel {
//    fun addDisposable(disposable: Disposable?)

    fun onDetach(source: LifecycleOwner)

}