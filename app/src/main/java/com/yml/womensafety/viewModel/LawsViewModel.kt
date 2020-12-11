package com.yml.womensafety.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat
import com.yml.womensafety.repository.LawsRepository

class LawsViewModel(application: Application) : AndroidViewModel(application) {
    var lawsDataSet = ArrayList<LawsAndEscapeThreat>()
    var lawsData = MutableLiveData<List<LawsAndEscapeThreat>>()
    lateinit var lawsRepository: LawsRepository
    fun initializeRepository() {
        lawsRepository = LawsRepository(getApplication())
        lawsDataSet = lawsRepository.getLaws()
    }

    fun getLaws(): LiveData<List<LawsAndEscapeThreat>> {
        lawsData.value = lawsDataSet
        return lawsData
    }
}