package com.yml.womensafety

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LawsAdapter(private val lawList: ArrayList<Laws>) :
    RecyclerView.Adapter<LawsAdapter.LawsVH>() {

    class LawsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var lawTitle = itemView.findViewById(R.id.lawTitle) as TextView
        var lawDescription = itemView.findViewById(R.id.lawDescription) as TextView
        var btnExpandLawDescription =
            itemView.findViewById(R.id.btnExpandLawDescription) as ImageButton
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LawsAdapter.LawsVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.law_item, parent, false)
        return LawsVH(v)
    }

    override fun onBindViewHolder(holder: LawsAdapter.LawsVH, position: Int) {
        //holder.bindItems(lawList[position])
        val laws: Laws = lawList[position]
        var isBtnExpanded: Boolean = lawList[position].expandable

        holder.lawTitle.text = laws.lawTitle
        holder.lawDescription.text = laws.lawDescription

        holder.btnExpandLawDescription.setOnClickListener {
            if (isBtnExpanded) {
                holder.btnExpandLawDescription.setImageResource(R.drawable.ic_arrow_up)
                holder.lawDescription.visibility = View.VISIBLE
                isBtnExpanded = !isBtnExpanded
            } else {
                holder.btnExpandLawDescription.setImageResource(R.drawable.ic_arrow_down)
                holder.lawDescription.visibility = View.GONE
                isBtnExpanded = true
            }
        }
    }

    override fun getItemCount(): Int {
        return lawList.size
    }
}