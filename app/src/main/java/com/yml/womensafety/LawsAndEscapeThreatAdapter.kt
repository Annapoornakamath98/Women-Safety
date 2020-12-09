package com.yml.womensafety

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.navigationdrawer.LawsAndEscapeThreat

class LawsAndEscapeThreatAdapter(private val lawAndThreatList: ArrayList<LawsAndEscapeThreat>) :
    RecyclerView.Adapter<LawsAndEscapeThreatAdapter.LawsAndEscapeThreatVH>() {

    class LawsAndEscapeThreatVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById(R.id.lawTitle) as TextView
        var description = itemView.findViewById(R.id.lawDescription) as TextView
        var btnExpandDescription =
            itemView.findViewById(R.id.btnExpandLawDescription) as ImageButton
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LawsAndEscapeThreatVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.law_item, parent, false)
        return LawsAndEscapeThreatVH(v)
    }

    override fun onBindViewHolder(
        holder: LawsAndEscapeThreatVH,
        position: Int
    ) {
        val lawsAndThreat: LawsAndEscapeThreat = lawAndThreatList[position]
        var isBtnExpanded: Boolean = lawAndThreatList[position].expandable

        holder.title.text = lawsAndThreat.title
        holder.description.text = lawsAndThreat.description

        holder.btnExpandDescription.setOnClickListener {
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

    override fun getItemCount(): Int {
        return lawAndThreatList.size
    }
}