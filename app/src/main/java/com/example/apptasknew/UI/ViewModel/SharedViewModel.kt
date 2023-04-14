package com.example.apptasknew.UI.ViewModel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import com.example.apptasknew.R

class SharedViewModel (application: Application):AndroidViewModel(application) {

    fun verifyDataFromUser(title: String, description: String): Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description)){
            false
        } else !(title.isEmpty()||description.isEmpty())
    }

}