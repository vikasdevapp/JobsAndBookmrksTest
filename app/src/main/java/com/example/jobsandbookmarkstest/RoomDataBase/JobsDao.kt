package com.example.jobsandbookmarkstest.RoomDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JobsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJob(job: JobEntity)

    @Query("SELECT * FROM bookmarked_jobs")
    fun getAllBookmarkedJobs(): List<JobEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_jobs WHERE id = :jobId)")
    fun isJobBookmarked(jobId: Long): Boolean

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    fun deleteJobById(jobId: Long?)

}