package com.danielwaiguru.remoter.dashboard.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.dashboard.databinding.JobItemBinding
import com.danielwaiguru.remoter.shared.utils.ClickListener
import com.danielwaiguru.remoter.shared.utils.DateUtils

class JobsAdapter(
    private val clickListener: ClickListener<JobDomain>
    ): ListAdapter<JobDomain, JobsAdapter.JobsViewHolder>(COMPARATOR) {
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
        val jobItem = getItem(position)
        holder.run {
            bindItem(jobItem)
            itemView.setOnClickListener {
                clickListener(jobItem)
            }
        }
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
                    val date = DateUtils.parseJsonDate(job.publicationDate)
                    if (date != null) {
                        location.text = DateUtils.formatToFullDate(date)
                    }
                }
            }
    }
}