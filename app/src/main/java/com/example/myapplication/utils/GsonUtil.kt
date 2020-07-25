package com.example.myapplication.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtil {
    private var GSON: Gson? = null

    //JSON转换为对象1--普通类型
    fun <T> fromJson(json: String?, classOfT: Class<T>?): T? {
        return if (TextUtils.isEmpty(json) || classOfT == null) null else GSON!!.fromJson(
            json,
            classOfT
        )
    }

    init {
        GSON = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss") //序列化日期格式  "yyyy-MM-dd"
            .setPrettyPrinting() //自动格式化换行
            .create()
    }
}