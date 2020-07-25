package com.example.myapplication.mvp.model

import android.util.Log
import androidx.lifecycle.*

abstract class BaseModel : IModel, LifecycleEventObserver {

//    private var mCompositeDisposable: CompositeDisposable? = null
//
//    override fun addDisposable(disposable: Disposable?) {
//        if (mCompositeDisposable == null) {
//            mCompositeDisposable = CompositeDisposable()
//        }
//        disposable?.let { mCompositeDisposable?.add(it) }
//    }

    override fun onDetach() {
        unDispose()

    }

    private fun unDispose() {
//        mCompositeDisposable?.clear()  // 保证Activity结束时取消
//        mCompositeDisposable = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("onStateChangedonS", event.name)
        if (event == Lifecycle.Event.ON_DESTROY) {
            onDetach()
            source.lifecycle.removeObserver(this)
        }
    }

    abstract suspend fun getLoalData(): Any?
    abstract suspend fun getRemoteData(): Any?

}