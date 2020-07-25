package com.example.myapplication.ui

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.mvp.contract.FileContract
import com.example.myapplication.mvp.presenter.FilePresenter
import com.example.myapplication.R
import com.example.myapplication.adapter.FileListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class FileActivity : MvpActivity<FileContract.View, FileContract.Presenter>(),
    FileContract.View {

    private val tag = "FILEACTIVITY_LOG"
    private lateinit var filePresenter: FilePresenter
    private lateinit var fileListAdapter: FileListAdapter
    private lateinit var viewLayoutManager: LinearLayoutManager

    override fun createPresenter(): FileContract.Presenter =
        FilePresenter()

    override fun attachLayoutRes(): Int = R.layout.activity_main

    override fun initPresenter() {
        super.initPresenter()
        filePresenter = presenter as FilePresenter
    }

    override fun initView() {
        super.initView()
        fileListAdapter = FileListAdapter(filePresenter.fileList)
        viewLayoutManager = LinearLayoutManager(this)

        rv_fileList_filectivity.apply {
            setHasFixedSize(true)
            layoutManager = viewLayoutManager
            adapter = fileListAdapter
        }
    }

    override fun initData() {
        filePresenter.getFilesBean()
    }

    override fun getFilesBeanSuccess() {
        Log.d(tag, "${filePresenter.fileList.size}")
        fileListAdapter.notifyDataSetChanged()
    }

    override fun showError(errorMsg: String) {
        Log.d(tag, "showError")
    }


}