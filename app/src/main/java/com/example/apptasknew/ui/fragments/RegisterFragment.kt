package com.example.apptasknew.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apptasknew.R
import com.example.apptasknew.ui.viewmodel.UserViewModel
import com.example.apptasknew.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)


        val userName = binding.userNameRegister
        val userPassword = binding.userPasswordRegister
        val createAccountLink = binding.registerCreateUserLink
        val navigateToLogin = binding.registerlinkToLogin


        userViewModel.registerResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                "true" ->
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment )
                "false" ->
                    Toast.makeText(context, "Usuario ya existe", Toast.LENGTH_SHORT).show()

                else ->
                    Toast.makeText(context, "Ocurriò un error, escribenos", Toast.LENGTH_SHORT)
                        .show()
            }
        })


        createAccountLink.setOnClickListener {
            val userNameInput = userName.text.toString()
            val userPasswordInput = userPassword.text.toString()

            userViewModel.createUser(userNameInput, userPasswordInput)
        }

        navigateToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }


        return binding.root
    }

}