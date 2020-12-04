package com.yml.womensafety.navigationdrawer

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.yml.womensafety.R
import kotlinx.android.synthetic.main.contacts_list.view.*

class ContactsListAdapter(val mContext: Context, val layoutResId: Int, val contactsList: List<Contact>)
    :ArrayAdapter<Contact>(mContext,layoutResId,contactsList){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mContext)
        val view: View = layoutInflater.inflate(layoutResId,null)
        val tvName = view.findViewById<TextView>(R.id.contactsListName)
        val tvNumber = view.findViewById<TextView>(R.id.contactsListNumber)
        val contact = contactsList[position]
        tvName.text = contact.contactName
        tvNumber.text = contact.contactNumber
        return view
    }
}