package com.danielwaiguru.remoter.dashboard.presentation.views.jobslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.dashboard.R
import com.danielwaiguru.remoter.dashboard.databinding.FragmentJobsListBinding
import com.danielwaiguru.remoter.dashboard.presentation.adapters.JobsAdapter
import com.danielwaiguru.remoter.dashboard.presentation.views.dashboard.DashBoardViewModel
import com.danielwaiguru.remoter.shared.BindingFragment
import com.google.gson.Gson
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
        return JobsAdapter{
            navigateToDetails(it)
        }
    }
    private fun navigateToDetails(job: JobDomain) {
        val jobString = Gson().toJson(job)
        val deepLink = NavDeepLinkRequest.Builder
            .fromUri("remoter://jobDetails/${jobString}".toUri())
            .build()
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .build()
        findNavController().navigate(deepLink, navOptions)
    }
}