package com.yml.womensafety.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat
import com.yml.womensafety.repository.LawsRepository

class LawsViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var lawsData: MutableLiveData<List<LawsAndEscapeThreat>>
    fun initializeRepository() {
        val lawsRepository = LawsRepository(getApplication())
        lawsData = lawsRepository.getLaws()
    }

    fun getLaws(): LiveData<List<LawsAndEscapeThreat>> {
        return lawsData
    }
}