package com.yml.womensafety.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.navigationdrawer.SafetyTips
import com.yml.womensafety.repository.SafetyTipsRepository

class SafetyTipsViewModel(application: Application) : AndroidViewModel(application) {
    var safetyTipsDataSet = ArrayList<SafetyTips>()
    var safetyTipsData = MutableLiveData<List<SafetyTips>>()
    lateinit var safetyTipsRepository: SafetyTipsRepository
    fun initializeRepository() {
        safetyTipsRepository = SafetyTipsRepository(getApplication())
        safetyTipsDataSet = safetyTipsRepository.getSafetyTips()
    }

    fun getSafetyTips(): LiveData<List<SafetyTips>> {
        safetyTipsData.value = safetyTipsDataSet
        return safetyTipsData
    }
}