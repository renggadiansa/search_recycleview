package com.example.search_recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageAdapter(private var mList: List<LanguageData>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
        var langDescTv : TextView = itemView.findViewById(R.id.langDesc)
        var constraintLayout : View = itemView.findViewById(R.id.constraintLayout)

        fun collpaseExpendedView(){
            langDescTv.visibility = View.GONE
        }
    }

    fun setFilteredList(mList: List<LanguageData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val languageData = mList[position]
        holder.logo.setImageResource(languageData.logo)
        holder.titleTv.text = (languageData.title)
        holder.langDescTv.text = (languageData.desc)

        val isExpandable : Boolean = languageData.isExpandable
        holder.langDescTv.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener{
            isAnyItemExpanded(position)
            languageData.isExpandable = !languageData.isExpandable
            notifyItemChanged(position, Unit)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = mList.indexOfFirst {
            it.isExpandable
        }
        if (temp != -1 && temp != position){
            mList[temp].isExpandable = false
            notifyItemChanged(temp,  0)
        }
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isNotEmpty() && payloads[0] == 0){
            holder.collpaseExpendedView()
        }else{
            super.onBindViewHolder(holder, position, payloads)
        }

        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}