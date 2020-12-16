package com.yml.womensafety.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.HelplineNumbers
import com.yml.womensafety.repository.HelplineNumbersRepository

class HelplineNumbersViewModel : ViewModel() {
    var helplineNumbersDataSet = ArrayList<HelplineNumbers>()
    var helplineNumbersData = MutableLiveData<List<HelplineNumbers>>()
    lateinit var helplineNumbersRepository: HelplineNumbersRepository
    fun initializeRepository() {
        helplineNumbersRepository = HelplineNumbersRepository()
        helplineNumbersDataSet = helplineNumbersRepository.getHelplineNumbers()
    }

    fun getNumbers(): LiveData<List<HelplineNumbers>> {
        helplineNumbersData.value = helplineNumbersDataSet
        return helplineNumbersData
    }
}