package com.example.myapplication.mvp.model

import com.example.myapplication.mvp.contract.FileContract
import com.example.myapplication.bean.FilesBean
import com.example.myapplication.utils.GsonUtil

class FileModel : BaseModel(),
    FileContract.Model {

    private val mockData: String =
        "{\"data\":[{\"fid\":\"1184608338990730479\",\"pid\":\"1168862579561467828\",\"fn\":\"201912052220.jpg\",\"upt\":\"1511233318\",\"fs\":\"5228310\"},{\"fid\":\"1184608338990730480\",\"pid\":\"1168862579561467828\",\"fn\":\"2009_log.txt\",\"upt\":\"1511234318\",\"fs\":\"733188\"},{\"fid\":\"1184608338990730481\",\"pid\":\"1168862579561467828\",\"fn\":\"Microsoft Office 2016 for Mac 15.39.0 VL.zip\",\"upt\":\"1511235318\",\"fs\":\"9733188\"},{\"fid\":\"1184608338990730482\",\"pid\":\"1168862579561467828\",\"fn\":\"115cloudoffice.apk\",\"upt\":\"1511236318\",\"fs\":\"9933188\"},{\"fid\":\"1184608338990730483\",\"pid\":\"1168862579561467828\",\"fn\":\"思维导图.xmind\",\"upt\":\"1511237318\",\"fs\":\"5533188\"},{\"fid\":\"1184608338990730484\",\"pid\":\"1168862579561467828\",\"fn\":\"2012002122.png\",\"upt\":\"1511238318\",\"fs\":\"1733188\"},{\"fid\":\"1184608338990730485\",\"pid\":\"1168862579561467828\",\"fn\":\"Microsoft Office 2020 for windows 15.39.0 VL.zip\",\"upt\":\"1511239318\",\"fs\":\"22733188\"},{\"fid\":\"1184608338990730486\",\"pid\":\"1168862579561467828\",\"fn\":\"\\u5f55\\u5c4f_20180418_141015.mp4\",\"upt\":\"1511939318\",\"fs\":\"33733188\"},{\"fid\":\"1184608338990730487\",\"pid\":\"1168862579561467828\",\"fn\":\"Microsoft Office 2020 for windows 15.39.0 VL.zip\",\"upt\":\"1511269318\",\"fs\":\"3188\"},{\"fid\":\"1184608338990730488\",\"pid\":\"1168862579561467828\",\"fn\":\"edrawmanual-cn.pdf\",\"upt\":\"1511839318\",\"fs\":\"23333188\"},{\"fid\":\"1184608338990730489\",\"pid\":\"1168862579561467828\",\"fn\":\"115 会议.dmg\",\"upt\":\"1511339318\",\"fs\":\"3333188\"}],\"state\":true,\"message\":\"\"}"

    override suspend fun getFilesBean(): FilesBean? {
        // TODO: 2020/7/25 这里可以先获取本地数据，如果没有则获取模拟的数据
//        if () {
//
//        } else {
//
//        }
        return getRemoteData()
    }

    override suspend fun getLoalData(): FilesBean? {
        return null
    }

    override suspend fun getRemoteData(): FilesBean? {
        return GsonUtil.fromJson(mockData, FilesBean::class.java)
    }


}