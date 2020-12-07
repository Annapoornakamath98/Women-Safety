package com.yml.womensafety.navigationdrawer.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var contactsList: MutableList<Contact>
    private lateinit var contactsList1: ArrayList<Contact>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            ref = FirebaseDatabase.getInstance().getReference("contacts").child(user.uid)
        }

        contactsList = mutableListOf()
        contactsList1 = arrayListOf()
        contactSave.setOnClickListener {
            saveContactInfo()
        }
        btnViewContacts.setOnClickListener {
            viewContacts()
        }

//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                contactsList.clear()
//                if (snapshot.exists()) {
//                    for (i in snapshot.children) {
//                        val contact = i.getValue(Contact::class.java)
//                        contactsList.add(contact!!)
//                    }
//                    val adapter =
//                        ContactsListAdapter(view.context, R.layout.contacts_list, contactsList)
//                    contactsListView.adapter = adapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })


    }
    private fun viewContacts(){
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactsList.clear()
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val contact = i.getValue(Contact::class.java)
                        contactsList.add(contact!!)
                    }
                    val adapter =
                        view?.let { ContactsListAdapter(it.context, R.layout.contacts_list, contactsList) }
                        contactsListView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {

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

        val contactId = ref.push().key
        val contact = contactId?.let {
            Contact(
                it,
                contactName.text.toString(),
                contactNumber.text.toString()
            )
        }
        if (contactId != null) {
            ref.child(contactId).setValue(contact).addOnCompleteListener {
                Toast.makeText(view?.context, "Success", Toast.LENGTH_LONG).show()
            }
        }

    }

}