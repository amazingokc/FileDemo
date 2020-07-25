package com.example.myapplication.mvp.contract

import com.example.myapplication.bean.FilesBean
import com.example.myapplication.mvp.model.IModel
import com.example.myapplication.mvp.presenter.IPresenter
import com.example.myapplication.mvp.view.IView

interface FileContract {

    interface View : IView {
        fun getFilesBeanSuccess()
    }

    interface Presenter :
        IPresenter<View> {
        fun getFilesBean()

    }

    interface Model : IModel {
        suspend fun getFilesBean(): FilesBean?
    }
}