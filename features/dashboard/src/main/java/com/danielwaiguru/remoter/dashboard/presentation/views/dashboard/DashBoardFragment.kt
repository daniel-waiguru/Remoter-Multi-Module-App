package com.danielwaiguru.remoter.dashboard.presentation.views.dashboard

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
import com.danielwaiguru.remoter.dashboard.databinding.FragmentDashBoardBinding
import com.danielwaiguru.remoter.dashboard.presentation.adapters.JobsAdapter
import com.danielwaiguru.remoter.dashboard.presentation.adapters.PrefsJobsAdapter
import com.danielwaiguru.remoter.shared.BindingFragment
import com.google.gson.Gson
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
        getData()
        initListeners()
        val adapter = createJobsAdapter()
        val prefsJobsAdapter = createPrefsJobAdapter()
        setupPrefRv(prefsJobsAdapter)
        setupRecyclerViews(adapter)
        attachObservers(adapter, prefsJobsAdapter)
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
    private fun initListeners() {
        binding.showAllJobs.setOnClickListener {
            findNavController().navigate(
                DashBoardFragmentDirections.actionDashBoardFragmentToJobsListFragment()
            )
        }
    }

    private fun setupPrefRv(prefsJobsAdapter: PrefsJobsAdapter) {
        binding.jobsForMeRv.apply {
            adapter = prefsJobsAdapter
            setHasFixedSize(true)
        }
    }

    private fun createPrefsJobAdapter(): PrefsJobsAdapter {
        return PrefsJobsAdapter()
    }

    private fun getData() {
        viewModel.getCategoryJobs("software-dev")
    }

    private fun attachObservers(jobsAdapter: JobsAdapter, prefsJobsAdapter: PrefsJobsAdapter) {
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
        viewModel.preferencesJobs.observe(viewLifecycleOwner) { result ->
            when(result) {
                is ResultWrapper.Success -> {
                    binding.progressBar.visibility = View.GONE
                    prefsJobsAdapter.submitList(result.data)
                }
                is ResultWrapper.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResultWrapper.Error -> {
                    binding.progressBar.visibility = View.GONE
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
        return JobsAdapter {
            navigateToDetails(it)
        }
    }
}