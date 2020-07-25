package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.bean.FileBean
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class FileListAdapter(private var fileList: MutableList<FileBean>) :
    RecyclerView.Adapter<FileListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.filelistadapter_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvFileName.text = fileList[position].fn
        holder.tvFileSizeAndTime.text =
            handleTime(fileList[position].fs, fileList[position].upt)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFileName: TextView = itemView.findViewById(R.id.tv_file_name_filelistadapter)
        var tvFileSizeAndTime: TextView =
            itemView.findViewById(R.id.tv_file_size_time_filelistadapter)
    }

    //格式化文件大小与创建时间
    @SuppressLint("SimpleDateFormat")
    private fun handleTime(fs: String?, upt: String?): String {
        val size : Float? = fs?.toFloat()?.div((1024 * 1024))
        val handleSize : String? = String.format("%.2f", size)
        val time = SimpleDateFormat("yy-MM-DD hh-mm").format(upt?.toLong())
        return "${handleSize}M  $time"
    }
}