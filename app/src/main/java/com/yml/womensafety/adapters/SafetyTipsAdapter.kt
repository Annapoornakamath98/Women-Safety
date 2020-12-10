package com.yml.womensafety.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.SafetyTips

//Adapter to display Tips for women safety in recycler view
class SafetyTipsAdapter(private val tipsList: ArrayList<SafetyTips>) :
    RecyclerView.Adapter<SafetyTipsAdapter.SafetyTipsViewHolder>() {

    class SafetyTipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(safetyTip: SafetyTips) {
            val tips = itemView.findViewById(R.id.tipsText) as TextView
            tips.text = safetyTip.tip
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SafetyTipsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.safety_tip_item, parent, false)
        return SafetyTipsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SafetyTipsViewHolder, position: Int) {
        holder.bindItems(tipsList[position])
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }
}