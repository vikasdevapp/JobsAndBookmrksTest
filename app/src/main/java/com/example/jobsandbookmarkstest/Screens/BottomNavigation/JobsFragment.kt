package com.example.jobsandbookmarkstest.Screens.BottomNavigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobsandbookmarkstest.Adapter.JobDetailsAdapter
import com.example.jobsandbookmarkstest.Model.JobsModelResponse
import com.example.jobsandbookmarkstest.Model.JobsModelResponseData
import com.example.jobsandbookmarkstest.R
import com.example.jobsandbookmarkstest.Services.RetrofitClient
import com.example.jobsandbookmarkstest.databinding.FragmentJobsBinding
import com.example.jobsandbookmarkstest.databinding.OverlayLoaderBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsFragment : Fragment() {

    private lateinit var binding: FragmentJobsBinding
    private val jobsApi = RetrofitClient.jobsApi
    private var currentPage = 1
    private val pageSize = 5
    private var isLastPage = false
    private var isLoadingNextPage = false
    private val jobsList = mutableListOf<JobsModelResponseData>()
    private lateinit var jobDetailsAdapter: JobDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJobsBinding.inflate(layoutInflater, container, false)
        binding.overlayLoader.overlayProgressBar.visibility = View.VISIBLE

        jobDetailsAdapter = JobDetailsAdapter(jobsList, requireContext())
        binding.jobsRcv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.jobsRcv.adapter = jobDetailsAdapter

        getJobDetails(requireContext(), currentPage)

        binding.jobsRcv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoadingNextPage && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= pageSize
                    ) {
                        currentPage++
                        getJobDetails(requireContext(), currentPage)
                    }
                }
            }
        })

        return binding.root
    }

    private fun getJobDetails(context: Context, page: Int) {
        isLoadingNextPage = true  // Set loading state to true
        binding.overlayLoader.overlayProgressBar.visibility = View.VISIBLE

        val call = jobsApi.getTheJobDetails(page)

        call.enqueue(object : Callback<JobsModelResponse> {
            override fun onResponse(call: Call<JobsModelResponse>, response: Response<JobsModelResponse>) {
                binding.overlayLoader.overlayProgressBar.visibility = View.INVISIBLE
                isLoadingNextPage = false  // Reset loading state

                if (response.isSuccessful && response.code() == 200) {
                    val jobsResponse = response.body()
                    if (jobsResponse != null) {
                        if (jobsResponse.results.isNotEmpty()) {
                            jobsList.addAll(jobsResponse.results)
                            jobDetailsAdapter.notifyDataSetChanged()
                        } else {
                            isLastPage = true
                        }
                    } else {
                        Toast.makeText(context, "No Data Available", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<JobsModelResponse>, t: Throwable) {
                binding.overlayLoader.overlayProgressBar.visibility = View.INVISIBLE
                isLoadingNextPage = false  // Reset loading state
                Toast.makeText(context, "Please Check Your Internet Connection.. Please try again!!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}