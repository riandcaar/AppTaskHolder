package com.example.apptasknew.UI.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptasknew.Data.Entity.TaskEntity
import com.example.apptasknew.R

class ListTaskHolderAdapter: RecyclerView.Adapter<ListTaskHolderAdapter.MyViewHolderTaskHolder>(){

    var dataList = emptyList<TaskEntity>()
    class MyViewHolderTaskHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var titleTXT: TextView = itemView.findViewById(R.id.title_txt)
        var descriptionTXT: TextView = itemView.findViewById(R.id.description_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderTaskHolder {
        return MyViewHolderTaskHolder(LayoutInflater.from(parent.context).inflate(R.layout.rowlayout,parent,false))

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolderTaskHolder, position: Int) {
        holder.titleTXT.text = dataList[position].title
        holder.descriptionTXT.text = dataList[position].description

    }

    fun setData(taskData: List<TaskEntity>){
        this.dataList = taskData
        notifyDataSetChanged()
    }

}
