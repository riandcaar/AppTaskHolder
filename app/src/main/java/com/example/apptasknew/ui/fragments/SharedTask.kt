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
import com.example.apptasknew.databinding.FragmentSharedTaskBinding
import com.example.apptasknew.ui.List.HolderAdapter
import com.example.apptasknew.ui.List.ListTaskHolderAdapter
import com.example.apptasknew.ui.viewmodel.TaskViewModel


class SharedTask: Fragment() {

    private lateinit var binding: FragmentSharedTaskBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private val adapter: HolderAdapter by lazy { HolderAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shared_task, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        taskViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }

        return binding.root
    }
}