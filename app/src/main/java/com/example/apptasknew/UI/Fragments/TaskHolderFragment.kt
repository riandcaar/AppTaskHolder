package com.example.apptasknew.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptasknew.R
import com.example.apptasknew.UI.List.ListTaskHolderAdapter
import com.example.apptasknew.UI.ViewModel.RegisterViewModel
import com.example.apptasknew.databinding.FragmentTaskHolderBinding


class TaskHolderFragment : Fragment() {

    private lateinit var binding: FragmentTaskHolderBinding
    private val mTodoViewModel: RegisterViewModel by viewModels()
    private val adapter: ListTaskHolderAdapter by lazy { ListTaskHolderAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_holder, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        mTodoViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_taskHolderFragment_to_registerTaskFragment)
        }

        setHasOptionsMenu(true)
        return binding.root

    }
}