package com.example.jobsandbookmarkstest.Screens.BottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jobsandbookmarkstest.Adapter.BookmarkedJobsAdapter
import com.example.jobsandbookmarkstest.RoomDataBase.AppDatabase
import com.example.jobsandbookmarkstest.databinding.FragmentBookmarksBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var bookmarkedJobsAdapter: BookmarkedJobsAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDatabase.getInstance(requireContext())

        setupRecyclerView()
        loadBookmarkedJobs()
    }

    private fun setupRecyclerView() {
        bookmarkedJobsAdapter = BookmarkedJobsAdapter()
        binding.bookmarkRcv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarkedJobsAdapter
        }
    }

    private fun loadBookmarkedJobs() {
        lifecycleScope.launch {
            val bookmarkedJobs = withContext(Dispatchers.IO) {
                appDatabase.jobDao().getAllBookmarkedJobs()
            }
            bookmarkedJobsAdapter.submitList(bookmarkedJobs)
        }
    }
}