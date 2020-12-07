package com.yml.womensafety

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.navigationdrawer.SafetyTips

class TipsForWomenSafetyAdapter(private val tipsTextList: ArrayList<SafetyTips>) :
    RecyclerView.Adapter<TipsForWomenSafetyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TipsForWomenSafetyAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.women_safety_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TipsForWomenSafetyAdapter.ViewHolder, position: Int) {
        holder.bindItems(tipsTextList[position])
    }

    override fun getItemCount(): Int {
        return tipsTextList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: SafetyTips) {
            val tips = itemView.findViewById(R.id.tipsText) as TextView
            tips.text = user.tipsText
        }
    }
}