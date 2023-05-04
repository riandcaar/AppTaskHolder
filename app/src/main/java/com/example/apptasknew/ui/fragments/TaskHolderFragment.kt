package com.example.apptasknew.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptasknew.R
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.ui.List.ListTaskHolderAdapter
import com.example.apptasknew.databinding.FragmentTaskHolderBinding
import com.example.apptasknew.databinding.RowlayoutBinding
import com.example.apptasknew.ui.viewmodel.TaskViewModel
import com.example.apptasknew.ui.viewmodel.UserViewModel


class TaskHolderFragment : Fragment() {

    private lateinit var binding: FragmentTaskHolderBinding
    private lateinit var binding1: RowlayoutBinding
    private val userViewModel: UserViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var userId: String
    private lateinit var taskList: TaskEntity
    private var check = false


    private val adapter: ListTaskHolderAdapter by lazy { ListTaskHolderAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_holder, container, false)
        binding1 = DataBindingUtil.inflate(inflater, R.layout.rowlayout, container, false)


        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())



        arguments?.let {
            userId = it.getString("CURRENT_USER_ID").toString()
        }
        userViewModel.getUserById(userId.toLong())

        taskViewModel.getUserTask(userId.toLong())
        taskViewModel.listResult.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }





        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskHolderFragment_to_registerTaskFragment,
                Bundle().apply { putString("CURRENT_USER_ID", userId) })
        }

        binding.addTaskToCommunity.setOnClickListener {
            taskViewModel.listResult.observe(viewLifecycleOwner) { data -> taskViewModel.createCommunityTask(data)}

        }

        binding.seeTaskToCommunity.setOnClickListener {
            findNavController().navigate(R.id.action_taskHolderFragment_to_sharedTask)

        }



        setHasOptionsMenu(true)
        return binding.root

    }


}








//userViewModel.userIdResult.observe(viewLifecycleOwner, Observer {
//if (it.isNotEmpty())
// else {
//findNavController().navigate(R.id.action_taskHolderFragment_to_registerTaskFragment,Bundle().apply {putString("CURRENT_USER_ID",userId)

//})
//}})


//userViewModel.loginResult.observe(viewLifecycleOwner, Observer {
// if (it.isNotEmpty())
//   Toast.makeText(context, "Puedes crear nuevas tareas", Toast.LENGTH_SHORT).show()
//else
//findNavController().navigate(R.id.action_taskHolderFragment_to_registerTaskFragment, Bundle().apply {putString("CURRENT_USER_ID",it)})
//})


//este es el navigation
//