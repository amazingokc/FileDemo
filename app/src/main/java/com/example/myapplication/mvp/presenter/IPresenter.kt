package com.example.myapplication.mvp.presenter

import com.example.myapplication.mvp.view.IView

interface IPresenter<V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView: V?)

    /**
     * 解绑 View
     */
   fun detachView()
}