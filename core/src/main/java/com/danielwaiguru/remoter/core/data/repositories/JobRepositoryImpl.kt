package com.danielwaiguru.remoter.core.data.repositories

import com.danielwaiguru.remoter.core.data.mappers.toDomain
import com.danielwaiguru.remoter.core.data.remote.RemotiveApiService
import com.danielwaiguru.remoter.core.data.util.ResultWrapper
import com.danielwaiguru.remoter.core.data.util.safeCall
import com.danielwaiguru.remoter.core.domain.models.JobDomain
import com.danielwaiguru.remoter.core.domain.repositories.JobRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class JobRepositoryImpl(
    private val apiService: RemotiveApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): JobRepository {
    override suspend fun getAllJobs(): ResultWrapper<List<JobDomain>> = safeCall(ioDispatcher) {
        apiService.getAllJobs().jobs.map { jobDto ->
            jobDto.toDomain()
        }
    }

    override suspend fun searchAJob(searchTerm: String): ResultWrapper<List<JobDomain>> =
        safeCall(ioDispatcher) {
            apiService.searchJob(searchTerm).jobs.map { jobDto ->
                jobDto.toDomain()
            }
        }
}