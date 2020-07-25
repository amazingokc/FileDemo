package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.bean.FileBean
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class FileListAdapter(private var fileList: MutableList<FileBean>?) :
    RecyclerView.Adapter<FileListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.filelistadapter_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (fileList == null) 0 else fileList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvFileName.text = fileList?.get(position)?.fn
        holder.tvFileSizeAndTime.text =
            handleTime(fileList?.get(position)?.fs, fileList?.get(position)?.upt)
        holder.clItem.setOnLongClickListener {
            Log.e("", "")
            clickListener?.clickItem(position)
            true
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvFileName: TextView = itemView.findViewById(R.id.tv_file_name_filelistadapter)
        var clItem: ConstraintLayout = itemView.findViewById(R.id.cl_item_filelistadapter)
        var tvFileSizeAndTime: TextView =
            itemView.findViewById(R.id.tv_file_size_time_filelistadapter)
    }

    //格式化文件大小与创建时间
    @SuppressLint("SimpleDateFormat")
    private fun handleTime(fs: String?, upt: String?): String {
        val size: Float? = fs?.toFloat()?.div((1024 * 1024))
        val handleSize: String? = String.format("%.2f", size)
        val time = SimpleDateFormat("yyyy-MM-dd hh:mm").format(1000 * upt?.toLong()!!)
        return "${handleSize}M   $time"
    }

    private var clickListener: ClickListener? = null

    public fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun clickItem(position: Int)
    }
}