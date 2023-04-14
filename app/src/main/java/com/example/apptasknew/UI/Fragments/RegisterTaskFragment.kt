package com.example.apptasknew.UI.Fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apptasknew.Data.Entity.TaskEntity
import com.example.apptasknew.R
import com.example.apptasknew.UI.ViewModel.RegisterViewModel
import com.example.apptasknew.UI.ViewModel.SharedViewModel
import com.example.apptasknew.databinding.FragmentRegisterTaskBinding

class RegisterTaskFragment : Fragment() {

    private lateinit var binding: FragmentRegisterTaskBinding
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val mTaskViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_register_task, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.add_fragment_button, menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (item.itemId == R.id.menu_add) {
                insertDataToDb()
            }
            return super.onOptionsItemSelected(item)

    }


    private fun insertDataToDb() {

        val title_et = binding.titleEt
        val mTitle = title_et.text.toString()

        val description_et = binding.registerTaskField
        val mDescription = description_et.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle,mDescription)
        if (validation){
            val newData = TaskEntity(
                0,
                mTitle,
                mDescription
            )
            mTaskViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Se agrego la tarea", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerTaskFragment_to_taskHolderFragment)
        }else{
            Toast.makeText(requireContext(), "Rellene toda la info", Toast.LENGTH_SHORT).show()
        }
    }
}
