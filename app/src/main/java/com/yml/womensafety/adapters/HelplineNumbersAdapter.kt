package com.yml.womensafety.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.HelplineNumbers

class HelplineNumbersAdapter(private val helplineNumbersList: ArrayList<HelplineNumbers>) :
    RecyclerView.Adapter<HelplineNumbersAdapter.HelplineNumbersViewHolder>() {

    class HelplineNumbersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var numbers: TextView = itemView.findViewById(R.id.tvHelplineNumbers)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelplineNumbersViewHolder {
        return HelplineNumbersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.helpline_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HelplineNumbersViewHolder, position: Int) {
        val helplineNumbers: HelplineNumbers = helplineNumbersList[position]
        holder.apply {
            numbers.text = numbers.context.getString(helplineNumbers.helplineNumberID)
        }
    }

    override fun getItemCount(): Int {
        return helplineNumbersList.size
    }
}