package com.example.myapplication.mvp.contract

import com.example.myapplication.bean.FileBean
import com.example.myapplication.bean.FilesBean
import com.example.myapplication.mvp.model.IModel
import com.example.myapplication.mvp.presenter.IPresenter
import com.example.myapplication.mvp.view.IView

interface FileContract {

    interface View : IView {

        fun getFilesBeanSuccess()
        fun addFileSuccess()
        fun deleteFileSuccess(deletePosition: Int)
    }

    interface Presenter : IPresenter<View> {
        fun getFilesBean()
        fun addFile()
        fun deleteFile(deletePosition: Int)
    }

    interface Model : IModel {
        suspend fun getFilesBean(): FilesBean?
        fun addFile(): FileBean?
        fun deleteFile(fileBean: FileBean)
    }
}