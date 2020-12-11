package com.yml.womensafety.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat
import com.yml.womensafety.repository.LawsRepository

class LawsViewModel() : ViewModel() {
    var lawsDataSet = ArrayList<LawsAndEscapeThreat>()
    var lawsData = MutableLiveData<List<LawsAndEscapeThreat>>()
    lateinit var lawsRepository: LawsRepository
    fun initializeRepository() {
        lawsRepository = LawsRepository()
        lawsDataSet = lawsRepository.getLaws()
    }

    fun getLaws(): LiveData<List<LawsAndEscapeThreat>> {
        lawsData.value = lawsDataSet
        return lawsData
    }
}