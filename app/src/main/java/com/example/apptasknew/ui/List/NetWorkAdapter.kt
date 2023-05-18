package com.example.apptasknew.ui.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptasknew.R
import com.example.apptasknew.network.dtoRepository.TaskDto

class NetWorkAdapter: RecyclerView.Adapter<NetWorkAdapter.NetWorkHolderView>() {

    var dataList = emptyList<TaskDto>()

    class NetWorkHolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTXT: TextView = itemView.findViewById(R.id.title_txt)
        var descriptionTXT: TextView = itemView.findViewById(R.id.description_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetWorkHolderView {
        return NetWorkHolderView(
            LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: NetWorkHolderView, position: Int) {
        holder.titleTXT.text = dataList[position].title
        holder.descriptionTXT.text = dataList[position].body

    }

    fun setData(taskData: List<TaskDto>) {
        this.dataList = taskData
        notifyDataSetChanged()
    }











}