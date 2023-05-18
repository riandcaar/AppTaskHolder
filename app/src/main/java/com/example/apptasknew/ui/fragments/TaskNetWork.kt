package com.example.apptasknew.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptasknew.R
import com.example.apptasknew.databinding.FragmentTaskHolderBinding
import com.example.apptasknew.databinding.FragmentTaskNetworkBinding
import com.example.apptasknew.ui.List.NetWorkAdapter
import com.example.apptasknew.ui.viewmodel.TaskViewModel

class TaskNetWork: Fragment() {

    private lateinit var binding: FragmentTaskNetworkBinding
    private val adapter: NetWorkAdapter by lazy { NetWorkAdapter() }
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_network, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        taskViewModel.getTaskFromNetwork()

        taskViewModel.task.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }


        return binding.root

    }
}