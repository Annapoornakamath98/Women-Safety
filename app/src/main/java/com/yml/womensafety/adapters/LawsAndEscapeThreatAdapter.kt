package com.yml.womensafety.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat

class LawsAndEscapeThreatAdapter(private val lawAndThreatList: ArrayList<LawsAndEscapeThreat>) :
    RecyclerView.Adapter<LawsAndEscapeThreatAdapter.LawsAndEscapeThreatViewHolder>() {

    class LawsAndEscapeThreatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.lawTitle)
        var description: TextView = itemView.findViewById(R.id.lawDescription)
        var btnExpandDescription: ImageButton =
            itemView.findViewById(R.id.btnExpandLawDescription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LawsAndEscapeThreatViewHolder {
        return LawsAndEscapeThreatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.law_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: LawsAndEscapeThreatViewHolder,
        position: Int
    ) {
        val lawsAndThreat: LawsAndEscapeThreat = lawAndThreatList[position]
        var isBtnExpanded: Boolean = lawAndThreatList[position].expandable

        holder.apply {
            title.text = title.context.getString(lawsAndThreat.titleResID)
            description.text = description.context.getString(lawsAndThreat.descriptionResID)
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
        return lawAndThreatList.size
    }
}