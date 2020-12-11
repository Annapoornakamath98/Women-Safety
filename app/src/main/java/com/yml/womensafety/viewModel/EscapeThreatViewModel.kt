package com.yml.womensafety.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat
import com.yml.womensafety.repository.EscapeThreatRepository

class EscapeThreatViewModel() : ViewModel() {
    private var escapeThreatData = MutableLiveData<List<LawsAndEscapeThreat>>()
    private var escapeThreatDataSet = ArrayList<LawsAndEscapeThreat>()
    lateinit var escapeThreatRepository: EscapeThreatRepository

    fun initializeRepository() {
        escapeThreatRepository = EscapeThreatRepository()
        escapeThreatDataSet = escapeThreatRepository.getEscapeThreatData()
    }

    fun getEscapeThreatData(): LiveData<List<LawsAndEscapeThreat>> {
        escapeThreatData.value = escapeThreatDataSet
        return escapeThreatData
    }
}