package com.yml.womensafety.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat
import com.yml.womensafety.repository.EscapeThreatRepository

class EscapeThreatViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var escapeThreatData: MutableLiveData<List<LawsAndEscapeThreat>>

    fun initializeRepository() {
        val escapeThreatRepository = EscapeThreatRepository(getApplication())
        escapeThreatData = escapeThreatRepository.getEscapeThreatData()
    }

    fun getEscapeThreatData(): LiveData<List<LawsAndEscapeThreat>> {
        return escapeThreatData
    }
}