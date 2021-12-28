package com.danielwaiguru.remoter.core.domain.use_cases

import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.core.domain.repositories.JobsRepository

class GetCategoryJobs(private val jobsRepository: JobsRepository) {
    suspend operator fun invoke(category: String): ResultWrapper<List<JobDomain>> =
        jobsRepository.getCategoryJos(category)
}