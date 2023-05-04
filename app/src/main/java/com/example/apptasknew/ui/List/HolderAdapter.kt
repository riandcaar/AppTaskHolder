package com.example.apptasknew.ui.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptasknew.R
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.entity.UserEntity

class HolderAdapter: RecyclerView.Adapter<HolderAdapter.TaskHolderView>() {

    var dataList = emptyList<TaskEntity>()

    class TaskHolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTXT: TextView = itemView.findViewById(R.id.title_txt)
        var descriptionTXT: TextView = itemView.findViewById(R.id.description_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolderView {
        return TaskHolderView(
            LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TaskHolderView, position: Int) {
        holder.titleTXT.text = dataList[position].title
        holder.descriptionTXT.text = dataList[position].description

    }

    fun setData(taskData: List<TaskEntity>) {
        this.dataList = taskData
        notifyDataSetChanged()
    }

}

