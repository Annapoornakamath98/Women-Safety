package com.yml.womensafety.navigationdrawer

import androidx.annotation.StringRes

data class SafetyTips(
    @StringRes val tipID: Int,
    @StringRes val tipDescriptionResID: Int,
    var expandable: Boolean = true
)