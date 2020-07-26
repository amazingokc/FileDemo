package com.example.myapplication.mvp.model

import android.util.Log
import com.example.myapplication.bean.FileBean
import com.example.myapplication.mvp.contract.FileContract
import com.example.myapplication.bean.FilesBean
import com.example.myapplication.db.DataBaseUtil
import com.example.myapplication.utils.GsonUtil
import kotlin.random.Random

class FileModel : BaseModel(),
    FileContract.Model {

    private val tag = "FileModel_tag"

    private val mockData: String =
        "{\"data\":[{\"fid\":\"1184608338990730479\",\"pid\":\"1168862579561467828\",\"fn\":\"201912052220.jpg\",\"upt\":\"1511233318\",\"fs\":\"5228310\"},{\"fid\":\"1184608338990730480\",\"pid\":\"1168862579561467828\",\"fn\":\"2009_log.txt\",\"upt\":\"1511234318\",\"fs\":\"733188\"},{\"fid\":\"1184608338990730481\",\"pid\":\"1168862579561467828\",\"fn\":\"Microsoft Office 2016 for Mac 15.39.0 VL.zip\",\"upt\":\"1511235318\",\"fs\":\"9733188\"},{\"fid\":\"1184608338990730482\",\"pid\":\"1168862579561467828\",\"fn\":\"115cloudoffice.apk\",\"upt\":\"1511236318\",\"fs\":\"9933188\"},{\"fid\":\"1184608338990730483\",\"pid\":\"1168862579561467828\",\"fn\":\"思维导图.xmind\",\"upt\":\"1511237318\",\"fs\":\"5533188\"},{\"fid\":\"1184608338990730484\",\"pid\":\"1168862579561467828\",\"fn\":\"2012002122.png\",\"upt\":\"1511238318\",\"fs\":\"1733188\"},{\"fid\":\"1184608338990730485\",\"pid\":\"1168862579561467828\",\"fn\":\"Microsoft Office 2020 for windows 15.39.0 VL.zip\",\"upt\":\"1511239318\",\"fs\":\"22733188\"},{\"fid\":\"1184608338990730486\",\"pid\":\"1168862579561467828\",\"fn\":\"\\u5f55\\u5c4f_20180418_141015.mp4\",\"upt\":\"1511939318\",\"fs\":\"33733188\"},{\"fid\":\"1184608338990730487\",\"pid\":\"1168862579561467828\",\"fn\":\"Microsoft Office 2020 for windows 15.39.0 VL.zip\",\"upt\":\"1511269318\",\"fs\":\"3188\"},{\"fid\":\"1184608338990730488\",\"pid\":\"1168862579561467828\",\"fn\":\"edrawmanual-cn.pdf\",\"upt\":\"1511839318\",\"fs\":\"23333188\"},{\"fid\":\"1184608338990730489\",\"pid\":\"1168862579561467828\",\"fn\":\"115 会议.dmg\",\"upt\":\"1511339318\",\"fs\":\"3333188\"}],\"state\":true,\"message\":\"\"}"
    private var filesBean: FilesBean? = null


    override suspend fun getFilesBean(): FilesBean? {
        //优先获取本地数据
        filesBean = getLoalData()
        //如果本地没有数据则获取接口数据
        if (filesBean == null) {
            filesBean = getRemoteData()
        }
        return filesBean;
    }

    override suspend fun addFile(): FileBean? {
        val fileBean = FileBean(
            Random.nextInt(1, 100000).toString(),
            Random.nextInt(1, 100000).toString(),
            "fileName${Random.nextInt(1, 100000).toString()}.png",
            "${System.currentTimeMillis() / 1000}",
            "${5 * 1024 * 1024}"
        )

        filesBean?.let {
            it.data?.add(fileBean)
            //新增的数据插入数据库
            DataBaseUtil.insertOrReplaceFile(fileBean)
        }
        return fileBean
    }

    override suspend fun deleteFile(fileBean: FileBean) {
        filesBean?.data?.remove(fileBean)
        DataBaseUtil.deleteFile(fileBean)
    }

    override suspend fun getLoalData(): FilesBean? {
        val fileList: MutableList<FileBean>? = DataBaseUtil.retrieveFileList()
        return if (fileList != null && fileList.size > 0) {
            Log.d(tag, "获取到的本地数据Size：${fileList.size}")
            FilesBean(
                true,
                "",
                fileList
            )
        } else {
            null
        }
    }

    override suspend fun getRemoteData(): FilesBean? {
        filesBean = GsonUtil.fromJson(mockData, FilesBean::class.java)

        //将RemoteData保存到本地数据库
        filesBean?.data?.forEach {
            DataBaseUtil.insertOrReplaceFile(it)
        }
        Log.d(tag, "获取到的网络数据Size：${filesBean?.data?.size}")
        return filesBean
    }


}