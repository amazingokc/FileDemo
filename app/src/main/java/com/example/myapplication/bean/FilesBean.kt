package com.example.myapplication.bean

data class FilesBean(
    var state: Boolean?,
    var message: String?,
    var data: MutableList<FileBean>?
)