package com.yml.womensafety.navigationdrawer

import androidx.annotation.StringRes

data class LawsAndEscapeThreat(
    @StringRes val titleResID: Int,
    @StringRes val descriptionResID: Int,
    var expandable: Boolean = true
)