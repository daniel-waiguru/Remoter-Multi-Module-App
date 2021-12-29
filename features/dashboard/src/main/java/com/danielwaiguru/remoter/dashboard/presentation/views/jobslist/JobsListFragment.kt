package com.danielwaiguru.remoter.dashboard.presentation.views.jobslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.dashboard.databinding.FragmentJobsListBinding
import com.danielwaiguru.remoter.dashboard.presentation.adapters.JobsAdapter
import com.danielwaiguru.remoter.dashboard.presentation.views.dashboard.DashBoardViewModel
import com.danielwaiguru.remoter.shared.BindingFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class JobsListFragment : BindingFragment<FragmentJobsListBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentJobsListBinding::inflate

    private val viewModel: DashBoardViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        attachObservers(adapter)
    }

    private fun attachObservers(jobsAdapter: JobsAdapter) {
        viewModel.allJobs.observe(viewLifecycleOwner) { result ->
            when(result) {
                is ResultWrapper.Success -> {
                    binding.progressBar1.visibility = View.GONE
                    jobsAdapter.submitList(result.data)
                }
                is ResultWrapper.Loading -> {
                    binding.progressBar1.visibility = View.VISIBLE
                }
                is ResultWrapper.Error -> {
                    binding.progressBar1.visibility = View.GONE
                }
            }
        }
    }

    private fun setupRecyclerView(jobsAdapter: JobsAdapter) {
        binding.allJobsRv.apply {
            adapter = jobsAdapter
            setHasFixedSize(true)
        }
    }

    private fun createAdapter(): JobsAdapter {
        return JobsAdapter()
    }
}