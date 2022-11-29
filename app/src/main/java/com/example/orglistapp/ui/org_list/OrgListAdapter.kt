package com.example.orglistapp.ui.org_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.orglistapp.R
import com.example.orglistapp.model.entities.OrganizationDTO


class OrgListAdapter(private var onItemClickListener: OnItemClickListener<OrganizationDTO>?) :
    RecyclerView.Adapter<OrgListAdapter.OrgListViewHolder>() {

    private var data: List<OrganizationDTO> = listOf()

    fun setData(data: List<OrganizationDTO>) {
        this.data = data
        this.notifyDataSetChanged()
    }

    fun removeListener() {
        onItemClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrgListViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_org_list_item, parent, false)
        return OrgListViewHolder(v)
    }

    override fun onBindViewHolder(holder: OrgListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OrgListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: OrganizationDTO) {
            with(itemView) {
                findViewById<ImageView>(R.id.iv_logo).load(data.img)
                findViewById<TextView>(R.id.tv_id).text = data.id
                findViewById<TextView>(R.id.tv_name).text = data.name
            }
            itemView.setOnClickListener { onItemClickListener?.onClick(data) }
        }
    }

}