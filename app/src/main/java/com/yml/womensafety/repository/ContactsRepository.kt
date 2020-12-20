package com.yml.womensafety.repository

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.yml.womensafety.ContactsResponse
import com.yml.womensafety.FirebaseUtil
import com.yml.womensafety.navigationdrawer.contacts.Contact

class ContactsRepository {
    private lateinit var contactsList: MutableList<String>
    private val appUser = FirebaseUtil.user?.currentUser
    private val databaseName = "contacts"
    private var databaseReference: DatabaseReference? =
        appUser?.uid?.let { FirebaseUtil.firebaseDatabase?.getReference(databaseName)?.child(it) }

    /**
     * This function gets the list of emergency contacts
     */
    fun getContactsList(response: ContactsResponse) {
        databaseReference?.keepSynced(true)
        databaseReference?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactsList = mutableListOf()
                contactsList.clear()
                if (snapshot.exists()) {
                    for (numbers in snapshot.children) {
                        val contact = numbers.getValue(Contact::class.java)
                        val value = contact?.contactNumber
                        if (value != null) {
                            contactsList.add(value)
                        }
                    }
                    response.onContactsReceiveSuccess(contactsList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                response.onContactsReceiveFailed(error.toException())
            }
        })

    }

}