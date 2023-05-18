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
import com.example.apptasknew.databinding.FragmentLoginBinding
import com.example.apptasknew.ui.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val userViewModel: UserViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)


        val userName = binding.etEmail
        val userPassword = binding.etPassword
        val userLogin = binding.btnLogin
        val navigateToCreateUser = binding.btnCreateUser




        userLogin.setOnClickListener {
            val userNameInput = userName.text.toString()
            val userPasswordInput = userPassword.text.toString()

            userViewModel.loginUser(userNameInput, userPasswordInput)
        }

        userViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty())
                findNavController().navigate(R.id.action_loginFragment_to_taskHolderFragment, Bundle().apply {putString("CURRENT_USER_ID",it)})
            else
                Toast.makeText(context, "", Toast.LENGTH_SHORT)
                    .show()

        })


        navigateToCreateUser.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        return binding.root
    }
}
