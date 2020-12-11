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
        var tips: TextView = itemView.findViewById(R.id.tipsText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SafetyTipsViewHolder {
        return SafetyTipsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.safety_tip_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SafetyTipsViewHolder, position: Int) {
        val safetyTips: SafetyTips = tipsList[position]
        holder.tips.text = holder.tips.context.getString(safetyTips.tipID)
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }
}