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
import com.example.apptasknew.databinding.FragmentRegisterTaskBinding
import com.example.apptasknew.databinding.FragmentTaskByIdNetworkBinding
import com.example.apptasknew.databinding.FragmentTaskHolderBinding
import com.example.apptasknew.ui.List.NetWorkAdapter
import com.example.apptasknew.ui.List.TaskIdNetwork
import com.example.apptasknew.ui.viewmodel.TaskViewModel
import com.example.apptasknew.ui.viewmodel.UserViewModel

class NetworkTaskById: Fragment() {

        private lateinit var binding: FragmentTaskByIdNetworkBinding
        private val taskViewModel: TaskViewModel by viewModels()
        private lateinit var userId: String
    private val userViewModel: UserViewModel by viewModels()

    private val adapter: TaskIdNetwork by lazy { TaskIdNetwork() }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_task_by_id_network, container, false)

            val recyclerView = binding.recyclerView
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())

            arguments?.let {
                userId = it.getString("CURRENT_USER_ID").toString()
            }
            userViewModel.getUserById(userId.toLong())

            taskViewModel.getTaskByIdNetwork(userId.toInt())

            taskViewModel.listTask.observe(viewLifecycleOwner) { data ->
                adapter.setData(data)
            }

            return binding.root

        }
    }