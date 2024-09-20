package com.example.jobsandbookmarkstest.RoomDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class JobEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String?,
    val companyName: String?,
    val jobLocationSlug: String?,
    val salaryMin: Long?,
    val salaryMax: Long?,
    val whatsappNo:String?,
    val feesText:String?,
    val jobCategory: String?,
    val jobRole: String?,
    val jobHours:String?,
    val createdOn: String?,
    val isBookmarked: Boolean = true
)