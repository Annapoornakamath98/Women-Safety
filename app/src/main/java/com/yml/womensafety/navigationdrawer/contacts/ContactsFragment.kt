package com.yml.womensafety.navigationdrawer.contacts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.yml.womensafety.FirebaseApplication
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    private lateinit var firebaseApplication: FirebaseApplication
    private lateinit var databaseReference: DatabaseReference

    private lateinit var contactsList: MutableList<Contact>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseApplication = FirebaseApplication()
        val user = firebaseApplication.u.currentUser

        if (user != null) {
            databaseReference = firebaseApplication.db.getReference("contacts").child(user.uid)
        }

        contactsList = mutableListOf()
        contactSave.setOnClickListener {
            saveContactInfo()
        }
        btnViewContacts.setOnClickListener {
            viewContacts()
        }

    }

    private fun viewContacts() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactsList.clear()
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val contact = i.getValue(Contact::class.java)
                        contactsList.add(contact!!)
                    }
                    val adapter =
                        view?.let {
                            ContactsListAdapter(
                                it.context,
                                R.layout.contacts_list,
                                contactsList
                            )
                        }
                    contactsListView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(view?.context, error.message, Toast.LENGTH_LONG).show()
            }

        })


    }

    private fun saveContactInfo() {
        if (contactName.text.toString().isEmpty()) {
            contactName.error = "This field can't be empty"
            contactName.requestFocus()
            return
        }
        if (contactNumber.text.toString().isEmpty()) {
            contactNumber.error = "This field can't be empty"
            contactNumber.requestFocus()
            return
        }

        val contactId = databaseReference.push().key
        val contact = contactId?.let {
            Contact(
                it,
                contactName.text.toString(),
                contactNumber.text.toString()
            )
        }
        if (contactId != null) {
            databaseReference.orderByChild("contactNumber").equalTo(contactNumber.text.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            contactNumber.error = "Number already exists"
                            contactNumber.requestFocus()
                        }
                        else {
                            databaseReference.child(contactId).setValue(contact).addOnCompleteListener {
                                Toast.makeText(view?.context, R.string.success, Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

        }

    }

}