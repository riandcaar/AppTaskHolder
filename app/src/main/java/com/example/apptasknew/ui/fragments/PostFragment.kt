package com.example.apptasknew.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apptasknew.R
import com.example.apptasknew.databinding.FragmentPostBinding
import com.example.apptasknew.ui.viewmodel.TaskViewModel
import com.example.apptasknew.ui.viewmodel.UserViewModel

class PostFragment: Fragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var userId: String


    private val taskViewModel: TaskViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)

        arguments?.let {
            userId = it.getString("CURRENT_USER_ID").toString()
        }
        userViewModel.getUserById(userId.toLong())

        var tilte = binding.titlePost
        var body = binding.bodyPost
        var createPost = binding.registerPost

        createPost.setOnClickListener{
            val tilteImput = tilte.text.toString()
            val bodyImput = body.text.toString()

            taskViewModel.postNetwork(tilteImput,bodyImput,userId.toInt())


            findNavController().navigate(
                R.id.action_postFragment_to_taskHolderFragment,
                Bundle().apply { putString("CURRENT_USER_ID", userId) })

        }

        taskViewModel.postResult.observe(viewLifecycleOwner, Observer {
            when (it) {
             true -> Toast.makeText(requireContext(), "Succesfully added", Toast.LENGTH_SHORT).show()

                else ->  Toast.makeText(context, "No se registro", Toast.LENGTH_SHORT)
                    .show()
            }

        })

        return binding.root
    }

}