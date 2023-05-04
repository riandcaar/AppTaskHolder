package com.example.apptasknew.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.R
import com.example.apptasknew.data.entity.UserEntity
import com.example.apptasknew.databinding.FragmentRegisterTaskBinding
import com.example.apptasknew.ui.viewmodel.TaskViewModel
import com.example.apptasknew.ui.viewmodel.UserViewModel

class RegisterTaskFragment : Fragment() {

    private lateinit var binding: FragmentRegisterTaskBinding
    private lateinit var userId: String

    private val taskViewModel: TaskViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register_task, container, false)

        arguments?.let {
            userId = it.getString("CURRENT_USER_ID").toString()
        }
        userViewModel.getUserById(userId.toLong())


        val taskTitle = binding.titleEt
        val taskDescription = binding.registerTaskField


        setHasOptionsMenu(true)

        return binding.root
    }

    //createAccountLink.setOnClickListener {
    //   val taskTitleInput = taskTitle.text.toString()
    // val taskDescriptionInput = taskDescription.text.toString()

    //taskViewModel.insertTask(taskTitleInput, taskDescriptionInput,userId.toLong())
    //}


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertTaskToUser()
        }
        return super.onOptionsItemSelected(item)

        //agregar tareas
    }

    private fun insertTaskToUser() {

        val title_et = binding.titleEt
        val Title = title_et.text.toString()

        val description_et = binding.registerTaskField
        val Description = description_et.text.toString()


        val validation = taskViewModel.verifyData(Title, Description, userId)
        if (validation) {
            val newTask = TaskEntity(
                Title,
                Description,
                0L
            )
            taskViewModel.insertTask(newTask,userId.toLong())
            Toast.makeText(requireContext(), "Succesfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.action_registerTaskFragment_to_taskHolderFragment,
                Bundle().apply { putString("CURRENT_USER_ID", userId) })
        } else {
            Toast.makeText(requireContext(), "Please fill out all fiel", Toast.LENGTH_SHORT).show()
        }
    }

}


