package com.example.myapplication.mvp.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.mvp.model.IModel
import com.example.myapplication.mvp.view.IView

abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleEventObserver {

    protected var model: M? = null
    protected var view: V? = null

    abstract fun createModel(): M?

    override fun attachView(mView: V?) {
        this.view = mView
        model = createModel()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (model != null && model is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(model as LifecycleObserver)
            }
        }
    }

    override fun detachView(source: LifecycleOwner) {
        source.lifecycle.removeObserver(this)
        this.model = null
        this.view = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            detachView(source)
        }
    }

}