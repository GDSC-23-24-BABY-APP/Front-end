package com.company.ait.tobemom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.company.ait.tobemom.utils.RetrofitClient2
import com.google.android.libraries.places.api.model.Place

class HospitalInfoAdapter(private val placeList: List<RetrofitClient2.Hospital>) :
    RecyclerView.Adapter<HospitalInfoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hospitalName: TextView = itemView.findViewById(R.id.hospitalName)
        val hospitalAddress: TextView = itemView.findViewById(R.id.hosptialAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hospital_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.hospitalName.text = place.name
        holder.hospitalAddress.text = place.address
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}
