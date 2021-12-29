package com.danielwaiguru.remoter.core.data.remote

import com.danielwaiguru.remoter.core.data.models.responses.JobsResponse
import com.danielwaiguru.remoter.shared.utils.ApiConstants.ALL_JOBS_ENDPOINT
import com.danielwaiguru.remoter.shared.utils.ApiConstants.SEARCH_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface RemotiveApiService {
    @GET(ALL_JOBS_ENDPOINT)
    suspend fun getAllJobs(): JobsResponse

    @GET(SEARCH_ENDPOINT)
    suspend fun searchJob(searchTerm: String): JobsResponse

    @GET(ALL_JOBS_ENDPOINT)
    suspend fun getCategoryJobs(
        @Query("category") category: String
    ): JobsResponse
}