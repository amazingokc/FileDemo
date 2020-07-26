package com.example.myapplication.db

import com.example.myapplication.FileApplication
import com.example.myapplication.bean.FileBean

object DataBaseUtil {

    /**
     * 插入或替换现有文件（单个文件）
     * */
    suspend fun insertOrReplaceFile(fileBean: FileBean) {
        FileApplication.instance().getDaoSession()?.fileBeanDao?.insertOrReplace(fileBean)
    }

    /**
     * 读取文件列表
     */
    suspend fun retrieveFileList(): MutableList<FileBean>? {
        return FileApplication.instance().getDaoSession()?.fileBeanDao?.loadAll()
    }

    /**
     * 删除单个文件
     */
    suspend fun deleteFile(fileBean: FileBean) {
        FileApplication.instance().getDaoSession()?.fileBeanDao?.delete(fileBean)
    }
}