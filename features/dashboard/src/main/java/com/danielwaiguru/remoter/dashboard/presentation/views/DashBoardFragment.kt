package com.danielwaiguru.remoter.dashboard.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.dashboard.databinding.FragmentDashBoardBinding
import com.danielwaiguru.remoter.dashboard.presentation.JobsAdapter
import com.danielwaiguru.remoter.shared.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashBoardFragment : BindingFragment<FragmentDashBoardBinding>() {
    private val viewModel: DashBoardViewModel by viewModel()
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentDashBoardBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = createJobsAdapter()
        setupRecyclerViews(adapter)
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

    private fun setupRecyclerViews(jobsAdapter: JobsAdapter) {
        with(binding) {
            allJobsRv.apply {
                adapter = jobsAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun createJobsAdapter(): JobsAdapter {
        return JobsAdapter()
    }
}