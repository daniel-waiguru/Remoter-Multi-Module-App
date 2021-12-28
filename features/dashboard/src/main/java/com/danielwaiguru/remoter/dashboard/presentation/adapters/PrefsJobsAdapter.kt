package com.danielwaiguru.remoter.dashboard.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.dashboard.databinding.JobItemBinding

class PrefsJobsAdapter: ListAdapter<JobDomain, PrefsJobsAdapter.JobsViewHolder>(COMPARATOR) {
    private object COMPARATOR: DiffUtil.ItemCallback<JobDomain>() {
        override fun areItemsTheSame(oldItem: JobDomain, newItem: JobDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JobDomain, newItem: JobDomain): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsViewHolder {
        return JobsViewHolder(
            JobItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: JobsViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    class JobsViewHolder(
        private val binding: JobItemBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bindItem(job: JobDomain) {
                with(binding) {
                    jobTitle.text = job.title
                    company.text = job.companyName
                    category.text = job.category
                    jobType.text = job.jobType
                    location.text = job.publicationDate
                }
            }
    }
}