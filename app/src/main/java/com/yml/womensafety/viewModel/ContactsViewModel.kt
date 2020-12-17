package com.yml.womensafety.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.ContactsResponse
import com.yml.womensafety.repository.ContactsRepository

class ContactsViewModel : ViewModel() {
    private val userContactsList = MutableLiveData<List<String>>()
    private lateinit var contactsRepository: ContactsRepository
    fun initializeRepository() {
        contactsRepository = ContactsRepository()
    }

    fun getContactsList(viewCallBack: ContactsResponse) {
        contactsRepository.getContactsList(object : ContactsResponse {
            override fun onContactsReceiveSuccess(userContacts: List<String>) {
                userContactsList.value = userContacts
                viewCallBack.onContactsReceiveSuccess(userContacts)
            }

            override fun onContactsReceiveFailed(throwable: Throwable) {
                viewCallBack.onContactsReceiveFailed(throwable)
            }
        })
    }
}
