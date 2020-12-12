package com.yml.womensafety.repository

import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.HelplineNumbers

class HelplineNumbersRepository {
    private val helplineNumbersDataSet = ArrayList<HelplineNumbers>()

    fun getHelplineNumbers(): ArrayList<HelplineNumbers> {
        displayNumbers()
        return helplineNumbersDataSet
    }

    private fun displayNumbers() {
        helplineNumbersDataSet.apply {
            add(HelplineNumbers(R.string.helpline_number_1))
            add(HelplineNumbers(R.string.helpline_number_2))
            add(HelplineNumbers(R.string.helpline_number_3))
            add(HelplineNumbers(R.string.helpline_number_4))
            add(HelplineNumbers(R.string.helpline_number_5))
            add(HelplineNumbers(R.string.helpline_number_6))
            add(HelplineNumbers(R.string.helpline_number_7))
            add(HelplineNumbers(R.string.helpline_number_8))
            add(HelplineNumbers(R.string.helpline_number_9))
            add(HelplineNumbers(R.string.helpline_number_10))
            add(HelplineNumbers(R.string.helpline_number_11))
            add(HelplineNumbers(R.string.helpline_number_12))
            add(HelplineNumbers(R.string.helpline_number_13))
            add(HelplineNumbers(R.string.helpline_number_14))
            add(HelplineNumbers(R.string.helpline_number_15))
            add(HelplineNumbers(R.string.helpline_number_16))
            add(HelplineNumbers(R.string.helpline_number_17))
            add(HelplineNumbers(R.string.helpline_number_18))
            add(HelplineNumbers(R.string.helpline_number_19))
            add(HelplineNumbers(R.string.helpline_number_20))
            add(HelplineNumbers(R.string.helpline_number_21))
            add(HelplineNumbers(R.string.helpline_number_22))
            add(HelplineNumbers(R.string.helpline_number_23))
            add(HelplineNumbers(R.string.helpline_number_24))
            add(HelplineNumbers(R.string.helpline_number_25))
            add(HelplineNumbers(R.string.helpline_number_26))
            add(HelplineNumbers(R.string.helpline_number_27))
            add(HelplineNumbers(R.string.helpline_number_28))
        }
    }
}