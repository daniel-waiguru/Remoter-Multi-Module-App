package com.danielwaiguru.remoter.job_details

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.job_details.databinding.FragmentJobDetailsBinding
import com.danielwaiguru.remoter.shared.BindingFragment
import com.danielwaiguru.remoter.shared.utils.DateUtils
import com.google.gson.Gson

class JobDetailsFragment : BindingFragment<FragmentJobDetailsBinding>() {
    private val viewModel: JobDetailsViewModel by viewModels()
    private lateinit var jobUrl: String
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
        viewModel.applyNavAction.observe(viewLifecycleOwner) {
            openApplyPage()
        }
    }

    private fun openApplyPage() {
        if (::jobUrl.isInitialized) {
            try {
                val url = Uri.parse(jobUrl)
                val browserIntent = Intent(Intent.ACTION_VIEW, url)
                startActivity(browserIntent)
            } catch (exception: ActivityNotFoundException) {
                exception.printStackTrace()
            }
        }
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun initListeners() {
        with(binding) {
            navBack.setOnClickListener {
                viewModel.onNavBackPressed()
            }
            btnApply.setOnClickListener {
                viewModel.onApplyPressed()
            }
        }
    }

    private fun showDetails(job: JobDomain?) {
        with(binding) {
            job?.let {
                jobUrl = it.url
                val date = DateUtils.parseJsonDate(it.publicationDate)
                if (date != null) {
                    publishDate.text = DateUtils.formatToFullDate(date)
                }
                companyName.text = job.companyName
                jobTitle.text = job.title
                category.text = job.category
                jobType.text = job.jobType
                jobLocation.text = job.candidateRequiredLocation
                tvJd.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(it.description)
                }
            }
        }
    }
}