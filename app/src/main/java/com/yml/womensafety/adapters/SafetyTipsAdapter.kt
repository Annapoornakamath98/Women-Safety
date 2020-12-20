package com.yml.womensafety.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.SafetyTips

class SafetyTipsAdapter() :
    RecyclerView.Adapter<SafetyTipsAdapter.SafetyTipsViewHolder>() {
    private val tipsList = mutableListOf<SafetyTips>()

    class SafetyTipsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.lawTitle)
        var description: TextView = itemView.findViewById(R.id.lawDescription)
        var btnExpandDescription: ImageButton =
            itemView.findViewById(R.id.btnExpandLawDescription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SafetyTipsViewHolder {
        return SafetyTipsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.law_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: SafetyTipsViewHolder,
        position: Int
    ) {
        val safetyTips: SafetyTips = tipsList[position]
        var isBtnExpanded: Boolean = tipsList[position].expandable

        holder.apply {
            title.text = title.context.getString(safetyTips.tipID)
            description.text = description.context.getString(safetyTips.tipDescriptionResID)
            btnExpandDescription.setOnClickListener {
                if (isBtnExpanded) {
                    holder.btnExpandDescription.setImageResource(R.drawable.ic_arrow_up)
                    holder.description.visibility = View.VISIBLE
                    isBtnExpanded = !isBtnExpanded
                } else {
                    holder.btnExpandDescription.setImageResource(R.drawable.ic_arrow_down)
                    holder.description.visibility = View.GONE
                    isBtnExpanded = true
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return tipsList.size
    }

    fun setList(tipsList: List<SafetyTips>) {
        this.tipsList.clear()
        this.tipsList.addAll(tipsList)
        notifyDataSetChanged()
    }
}