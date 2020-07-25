package com.example.myapplication.mvp.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.mvp.contract.FileContract
import com.example.myapplication.mvp.model.FileModel
import com.example.myapplication.bean.FileBean
import com.example.myapplication.ui.FileActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class FilePresenter : BasePresenter<FileContract.Model, FileContract.View>(),
    FileContract.Presenter {

    var fileList: MutableList<FileBean> = mutableListOf()

    override fun createModel(): FileContract.Model? =
        FileModel()

    override fun getFilesBean() {
        (view as FileActivity?)?.lifecycleScope?.launch(Dispatchers.Main) {
            view?.showLoading()
            try {
                withContext(Dispatchers.IO) {
                    fileList.clear()
                    model?.getFilesBean()?.data?.let { fileList.addAll(it) }
                }
                view?.getFilesBeanSuccess()
            } catch (e: Exception) {
                view?.showError(e.toString())
            }
            view?.hideLoading()
        }
    }

    override fun addFile() {
        (view as FileActivity?)?.lifecycleScope?.launch(Dispatchers.Main) {
            view?.showLoading()
            try {
                withContext(Dispatchers.IO) {
                    model?.addFile()?.let { fileList.add(0, it) }
                }
                view?.addFileSuccess()
            } catch (e: Exception) {
                view?.showError("添加文件失败${e.toString()}")
            }
            view?.hideLoading()
        }
    }

    override fun deleteFile(deletePosition: Int) {
        (view as FileActivity?)?.lifecycleScope?.launch(Dispatchers.Main) {
            view?.showLoading()
            try {
                withContext(Dispatchers.IO) {
                    model?.deleteFile(deletePosition)
                }
                view?.deleteFileSuccess()
            } catch (e: Exception) {
                view?.showError("删除文件失败${e.toString()}")
            }
            view?.hideLoading()
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        super.onStateChanged(source, event)
        if (event == Lifecycle.Event.ON_DESTROY) {
            view = null
            (view as FileActivity?)?.lifecycleScope?.cancel()
        }
    }


}