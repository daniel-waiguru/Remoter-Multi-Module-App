package com.danielwaiguru.remoter.core.domain.repositories

import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.core.domain.models.JobDomain

interface JobsRepository {
    suspend fun getAllJobs(): ResultWrapper<List<JobDomain>>
    suspend fun searchAJob(searchTerm: String): ResultWrapper<List<JobDomain>>

    suspend fun getCategoryJos(category: String): ResultWrapper<List<JobDomain>>
}