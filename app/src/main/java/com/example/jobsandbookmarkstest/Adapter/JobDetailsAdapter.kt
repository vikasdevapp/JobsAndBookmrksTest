package com.example.jobsandbookmarkstest.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsandbookmarkstest.Model.JobsModelResponseData
import com.example.jobsandbookmarkstest.R
import com.example.jobsandbookmarkstest.Screens.HomePage.ShowJobProperDetails
import com.google.gson.Gson

class JobDetailsAdapter(var dataList: List<JobsModelResponseData>, var context: Context) : RecyclerView.Adapter<JobDetailsAdapter.JobsHolder>() {

    inner class JobsHolder(var view: View): RecyclerView.ViewHolder(view){

        val onTapCard=view.findViewById<LinearLayout>(R.id.user_card_rem)
        val title=view.findViewById<TextView>(R.id.titleinputfield)
        val location=view.findViewById<TextView>(R.id.locationinputfield)
        val salary=view.findViewById<TextView>(R.id.salaryinputfield)
        val phone=view.findViewById<TextView>(R.id.phoneinputfield)

        fun bind(item: JobsModelResponseData) {
            onTapCard.setOnClickListener {
                val intent = Intent(context, ShowJobProperDetails::class.java)
                val gson = Gson()
                val json = gson.toJson(item)
                intent.putExtra("jobId", json)
                ContextCompat.startActivity(context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.jobscarlayout,parent,false)
        return JobsHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    override fun onBindViewHolder(holder: JobsHolder, position: Int) {
        val item = dataList[position]

        if (item.title.isNullOrEmpty()){
            holder.title.text="No title found"
        }
        else {
            holder.title.text=item.title
        }

        if(item.jobLocationSlug.isNullOrEmpty()){
            holder.location.text="No Location found"
        }else{
            holder.location.text=item.jobLocationSlug
        }

        if(item.whatsappNo.isNullOrEmpty()){
            holder.phone.text="No Phone found"
        }else{
            holder.phone.text= item.whatsappNo
        }

        if ("${item.salaryMin}-${item.salaryMax}".isNullOrEmpty()){
            holder.salary.text="No salary found"
        } else{
            holder.salary.text="${item.salaryMin} - ${item.salaryMax}"
        }

        holder.bind(dataList[position])

    }
}