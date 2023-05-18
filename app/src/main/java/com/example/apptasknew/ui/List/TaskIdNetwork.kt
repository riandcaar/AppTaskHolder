package com.example.apptasknew.ui.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptasknew.R
import com.example.apptasknew.network.dtoRepository.TaskDto

class TaskIdNetwork: RecyclerView.Adapter<TaskIdNetwork.IdViewNetwork>() {

    var dataList = emptyList<TaskDto>()

    class IdViewNetwork(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTXT: TextView = itemView.findViewById(R.id.title_txt)
        var descriptionTXT: TextView = itemView.findViewById(R.id.description_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IdViewNetwork {
        return IdViewNetwork(
            LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: IdViewNetwork, position: Int) {
        holder.titleTXT.text = dataList[position].title
        holder.descriptionTXT.text = dataList[position].body

    }

    fun setData(taskData: List<TaskDto>) {
        this.dataList = taskData
        notifyDataSetChanged()
    }



}