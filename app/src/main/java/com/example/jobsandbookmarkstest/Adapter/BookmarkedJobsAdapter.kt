package com.example.jobsandbookmarkstest.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsandbookmarkstest.RoomDataBase.JobEntity
import com.example.jobsandbookmarkstest.databinding.ItemBookmarkedJobBinding
import java.text.SimpleDateFormat
import java.util.Locale

class BookmarkedJobsAdapter : ListAdapter<JobEntity, BookmarkedJobsAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemBookmarkedJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class JobViewHolder(private val binding: ItemBookmarkedJobBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobEntity) {
            binding.apply {
                jobTitle.text = job.title
                companyName.text = job.companyName
                jobLocation.text = job.jobLocationSlug
                salary.text = "${job.salaryMin}, ${job.salaryMax}"
                phone.text = job.whatsappNo
                fees.text = job.feesText
                jobCategory.text = job.jobCategory
                jobRole.text = job.jobRole
                workingHours.text = job.jobHours
                createdOn.text = job.createdOn?.let { formatDate(it) } ?: "N/A"
            }
        }

        private fun formatSalary(min: String?, max: String?): String {
            return if (!min.isNullOrEmpty() && !max.isNullOrEmpty()) {
                "$min - $max"
            } else {
                "Salary not specified"
            }
        }

        private fun formatDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", Locale.US)
            val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.US)
            return try {
                val date = inputFormat.parse(dateString)
                outputFormat.format(date ?: return "Invalid date")
            } catch (e: Exception) {
                Log.e("DateFormatting", "Error parsing date: ${e.message}")
                "Invalid date"
            }
        }
    }

    private class JobDiffCallback : DiffUtil.ItemCallback<JobEntity>() {
        override fun areItemsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean {
            return oldItem == newItem
        }
    }
}