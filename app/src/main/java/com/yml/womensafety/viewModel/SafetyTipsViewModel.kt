package com.yml.womensafety.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.navigationdrawer.SafetyTips
import com.yml.womensafety.repository.SafetyTipsRepository

class SafetyTipsViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var safetyTipsList: MutableLiveData<List<SafetyTips>>
    fun initializeRepository() {
        val safetyTipsRepository = SafetyTipsRepository(getApplication())
        safetyTipsList = safetyTipsRepository.getSafetyTips()
    }

    fun getSafetyTips(): LiveData<List<SafetyTips>> {
        return safetyTipsList
    }
}