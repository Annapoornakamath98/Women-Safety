package com.yml.womensafety

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.navigationdrawer.EscapeThreat

class EscapeThreatAdapter(private val tipsTextList: ArrayList<EscapeThreat>) :
    RecyclerView.Adapter<EscapeThreatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EscapeThreatAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.escape_threat_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: EscapeThreatAdapter.ViewHolder, position: Int) {
        holder.bindItems(tipsTextList[position])
    }

    override fun getItemCount(): Int {
        return tipsTextList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: EscapeThreat) {
            val tips = itemView.findViewById(R.id.tipsText) as TextView
            tips.text = user.tipsText
        }
    }
}