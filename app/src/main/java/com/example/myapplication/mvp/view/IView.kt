package com.example.myapplication.mvp.view

interface IView {
    fun showLoading()

    fun hideLoading()

    fun showError(errorMsg: String)
}