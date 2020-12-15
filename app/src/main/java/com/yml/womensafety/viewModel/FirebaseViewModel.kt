package com.yml.womensafety.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.Response
import com.yml.womensafety.repository.FirebaseRepository

class FirebaseViewModel : ViewModel() {
    var userName = MutableLiveData<String>()
    var userEmail = MutableLiveData<String>()
    var userContact = MutableLiveData<String>()
    private lateinit var firebaseRepository: FirebaseRepository
    fun initializeRepository() {
        firebaseRepository = FirebaseRepository()
    }

    fun getUserName(viewCallBack: Response) {
        firebaseRepository.getUserName(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                userName.value = userDetails
                viewCallBack.onUserDetailReceiveSuccess(userDetails)
            }

        })
    }

    fun getUserEmail(viewCallBack: Response) {
        firebaseRepository.getUserEmail(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                userEmail.value = userDetails
                viewCallBack.onUserDetailReceiveSuccess(userDetails)
            }

        })
    }

    fun getUserContact(viewCallBack: Response) {
        firebaseRepository.getUserContact(object : Response {
            override fun onUserDetailReceiveSuccess(userDetails: String) {
                userContact.value = userDetails
                viewCallBack.onUserDetailReceiveSuccess(userDetails)
            }

        })
    }
}