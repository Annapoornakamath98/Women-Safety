package com.yml.womensafety

interface ContactsResponse {
    fun onContactsReceiveSuccess(userContacts: List<String>)
    fun onContactsReceiveFailed(throwable: Throwable)
}