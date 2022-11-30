package com.example.orglistapp.ui.org_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.orglistapp.R
import com.example.orglistapp.model.entities.Organization
import com.example.orglistapp.utils.BASE_URL


class OrgListAdapter(private var onItemClickListener: OnItemClickListener<Organization>?) :
    RecyclerView.Adapter<OrgListAdapter.OrgListViewHolder>() {

    private var data: List<Organization> = listOf()

    fun setData(data: List<Organization>) {
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

    override fun onBindViewHolder(holder: OrgListViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    inner class OrgListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Organization) {
            with(itemView) {
                findViewById<ImageView>(R.id.iv_logo).load(BASE_URL.plus(data.img)) {
                    crossfade(true)
                    placeholder(R.drawable.ic_no_photo_vector)
                    transformations(CircleCropTransformation())
                }
                findViewById<TextView>(R.id.tv_id).text = "id = ${data.id}"
                findViewById<TextView>(R.id.tv_name).text = data.name
            }
            itemView.setOnClickListener { onItemClickListener?.onClick(data) }
        }
    }

}