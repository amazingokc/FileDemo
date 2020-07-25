package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import com.example.myapplication.mvp.presenter.IPresenter
import com.example.myapplication.mvp.view.IView

abstract class MvpActivity<V : IView, P : IPresenter<V>> : AppCompatActivity(),
    IView {

    protected var presenter: P? = null
    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(attachLayoutRes())
        initPresenter()
        initView()
        initData()
    }

    @LayoutRes
    protected abstract fun attachLayoutRes(): Int

    @Suppress("UNCHECKED_CAST")
    open fun initPresenter() {
        presenter = createPresenter()
        presenter?.attachView(this as? V)
    }

    open fun initView() {

    }

    open fun initData() {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

}