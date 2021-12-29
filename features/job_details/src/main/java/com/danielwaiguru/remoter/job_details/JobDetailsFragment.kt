package com.danielwaiguru.remoter.job_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.job_details.databinding.FragmentJobDetailsBinding
import com.danielwaiguru.remoter.shared.BindingFragment
import com.google.gson.Gson

class JobDetailsFragment : BindingFragment<FragmentJobDetailsBinding>() {
    private val viewModel: JobDetailsViewModel by viewModels()
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentJobDetailsBinding::inflate

    private val args: JobDetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val job = Gson().fromJson(args.job, JobDomain::class.java)
        showDetails(job)
        initListeners()
        attachObservers()
    }

    private fun attachObservers() {
        viewModel.navigateBack.observe(viewLifecycleOwner) {
            navigateBack()
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun initListeners() {
        binding.navBack.setOnClickListener {
            viewModel.onNavBackPressed()
        }
    }

    private fun showDetails(job: JobDomain?) {
        with(binding) {
            companyName.text = job?.companyName
            jobTitle.text = job?.title
            category.text = job?.category
            jobType.text = job?.jobType
            jobLocation.text = job?.candidateRequiredLocation
            publishDate.text = job?.publicationDate
            tvJd.text = job?.description
        }
    }
}