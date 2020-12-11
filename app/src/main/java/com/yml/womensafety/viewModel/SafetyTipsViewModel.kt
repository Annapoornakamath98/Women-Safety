package com.yml.womensafety.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.SafetyTips
import com.yml.womensafety.repository.SafetyTipsRepository

class SafetyTipsViewModel() : ViewModel() {
    var safetyTipsDataSet = ArrayList<SafetyTips>()
    var safetyTipsData = MutableLiveData<List<SafetyTips>>()
    lateinit var safetyTipsRepository: SafetyTipsRepository
    fun initializeRepository() {
        safetyTipsRepository = SafetyTipsRepository()
        safetyTipsDataSet = safetyTipsRepository.getSafetyTips()
    }

    fun getSafetyTips(): LiveData<List<SafetyTips>> {
        safetyTipsData.value = safetyTipsDataSet
        return safetyTipsData
    }
}