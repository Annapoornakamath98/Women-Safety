package com.yml.womensafety.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat
import com.yml.womensafety.repository.EscapeThreatRepository

class EscapeThreatViewModel(application: Application) : AndroidViewModel(application) {
    private var escapeThreatData = MutableLiveData<List<LawsAndEscapeThreat>>()
    private var escapeThreatDataSet = ArrayList<LawsAndEscapeThreat>()
    lateinit var escapeThreatRepository: EscapeThreatRepository

    fun initializeRepository() {
        escapeThreatRepository = EscapeThreatRepository(getApplication())
        escapeThreatDataSet = escapeThreatRepository.getEscapeThreatData()
    }

    fun getEscapeThreatData(): LiveData<List<LawsAndEscapeThreat>> {
        escapeThreatData.value = escapeThreatDataSet
        return escapeThreatData
    }
}