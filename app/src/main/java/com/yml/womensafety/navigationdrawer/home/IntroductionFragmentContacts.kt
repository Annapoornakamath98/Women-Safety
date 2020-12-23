package com.yml.womensafety.navigationdrawer.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yml.womensafety.R
import com.yml.womensafety.authentication.LoginActivity
import kotlinx.android.synthetic.main.fragment_introduction_contacts.*

class IntroductionFragmentContacts : Fragment(R.layout.fragment_introduction_contacts) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSkipOne.setOnClickListener {
            startActivity(Intent(view.context, LoginActivity::class.java))
        }
    }
}