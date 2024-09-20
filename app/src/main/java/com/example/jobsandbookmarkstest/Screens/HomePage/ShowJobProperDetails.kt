package com.example.jobsandbookmarkstest.Screens.HomePage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.jobsandbookmarkstest.Model.JobsModelResponseData
import com.example.jobsandbookmarkstest.R
import com.example.jobsandbookmarkstest.RoomDataBase.AppDatabase
import com.example.jobsandbookmarkstest.RoomDataBase.JobEntity
import com.example.jobsandbookmarkstest.databinding.ActivityShowJobProperDetailsBinding
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class ShowJobProperDetails : AppCompatActivity() {

    private lateinit var binding: ActivityShowJobProperDetailsBinding
    private lateinit var data: JobsModelResponseData
    private lateinit var appDatabase: AppDatabase
    private var isBookmarked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowJobProperDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(applicationContext)
        setupAppBar()
        loadJobDetails()
    }

    private fun setupAppBar() {
        binding.showproperdetailsAppbar.apply {
            appBarText.text = "Job Proper Details"
            backBtn.setOnClickListener { finish() }
        }
    }

    private fun loadJobDetails() {
        val jobId = intent.getStringExtra("jobId")
        Log.d("Jobs", "Received JSON: $jobId")

        if (!jobId.isNullOrEmpty()) {
            try {
                data = Gson().fromJson(jobId, JobsModelResponseData::class.java)
                bindData()
                checkBookmarkStatus()
                setupBookmarkButton()
            } catch (e: JsonSyntaxException) {
                Log.e("Jobs", "JSON parsing error: ${e.message}")
                showError("Error loading job details")
            }
        } else {
            Log.e("Jobs", "Invalid JSON format.")
            showError("Invalid job data")
        }
    }

    private fun bindData() {
        binding.apply {
            titleinputproperdet.text = data.title.orDefault()
            locationProperDet.text = data.jobLocationSlug.orDefault()
            salaryProperDet.text = "${data.salaryMin}-${data.salaryMax}"
            phoneProperDet.text = data.whatsappNo.orDefault()
            companyNameProper.text = data.companyName.orDefault()
            jobcategoryProper.text = data.jobCategory.orDefault()
            roleProperDet.text = data.jobRole.orDefault()
            feeschargeProper.text = data.amount.orDefault()
            workingHoursProper.text = data.jobHours.orDefault()
            createdOnproper.text = data.createdOn?.let { formatDate(it) }.orDefault()
        }
    }

    private fun checkBookmarkStatus() {
        lifecycleScope.launch(Dispatchers.Main) {
            isBookmarked = withContext(Dispatchers.IO) {
                val jobId = data.id!!
                jobId?.let { appDatabase.jobDao().isJobBookmarked(it) } ?: false
            }
            updateBookmarkUI()
        }
    }
    private fun setupBookmarkButton() {
        binding.bookmarkthedetails.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                if (isBookmarked) {
                    unbookmarkJob()
                } else {
                    bookmarkJob()
                }
            }
        }
    }

    private suspend fun bookmarkJob() {
        withContext(Dispatchers.IO) {
            val jobEntity = JobEntity(
                id = data.id ?: return@withContext,
                title = data.title,
                companyName = data.companyName,
                jobLocationSlug = data.jobLocationSlug,
                salaryMin = data.salaryMin,
                salaryMax = data.salaryMax,
                whatsappNo = data.whatsappNo,
                feesText = data.feesCharged.toString(),
                jobCategory = data.jobCategory,
                jobRole = data.jobRole,
                jobHours = data.jobHours,
                createdOn = data.createdOn
            )
            appDatabase.jobDao().insertJob(jobEntity)
        }
        isBookmarked = true
        updateBookmarkUI()
        Toast.makeText(this, "Job bookmarked", Toast.LENGTH_SHORT).show()
    }

    private suspend fun unbookmarkJob() {
        withContext(Dispatchers.IO) {
            val jobId = data.id!!
            jobId?.let { appDatabase.jobDao().deleteJobById(it) }
        }
        isBookmarked = false
        updateBookmarkUI()
        Toast.makeText(this, "Job removed from bookmarks", Toast.LENGTH_SHORT).show()
    }

    private fun updateBookmarkUI() {
        val color = if (isBookmarked) {
            ContextCompat.getColor(this, R.color.primary)
        } else {
            ContextCompat.getColor(this, R.color.black)
        }
        binding.bookmarkthedetails.setColorFilter(color)
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

    private fun String?.orDefault() = this ?: "No data available"

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }
}