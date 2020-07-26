package com.example.myapplication

import android.app.Application
import com.example.greendao.gen.DaoMaster
import com.example.greendao.gen.DaoSession
import com.example.myapplication.db.MyOpenHelper

class FileApplication : Application() {

    private var daoSession: DaoSession? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        setDatabase()
    }

    private fun setDatabase() {
        val mHelper = MyOpenHelper(this, "file-db", null) //建库
        val mDaoMaster = DaoMaster(mHelper.writableDatabase)
        daoSession = mDaoMaster.newSession()
    }

    fun getDaoSession(): DaoSession? {
        return daoSession
    }

    companion object {
        private lateinit var instance: FileApplication
        fun instance() = instance
    }
}