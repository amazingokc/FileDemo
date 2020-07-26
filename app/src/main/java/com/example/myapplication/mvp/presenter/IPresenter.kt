package com.example.myapplication.mvp.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.myapplication.mvp.view.IView

interface IPresenter<V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView: V?)

    /**
     * 解绑 View
     */
   fun detachView(source: LifecycleOwner)
}